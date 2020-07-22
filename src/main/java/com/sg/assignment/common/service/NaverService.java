package com.sg.assignment.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.assignment.common.model.NaverUserInfo;
import com.sg.assignment.common.web.NaverAuthenticationToken;

@Service
public class NaverService {

	@Value("${naver.login.client-id}")
	private String clientId;

	@Value("${naver.login.client-secret}")
	private String clientSecret;

	@Value("${naver.login.callback-url}")
	private String callbackUrl;

	private String redirectURI;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostConstruct
	private void init() {
		try {
			this.redirectURI = URLEncoder.encode(callbackUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLoginApiUrl(HttpSession session) {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s", clientId, redirectURI, state);
		session.setAttribute("state", state);
		return apiURL;
	}

	public void applyNaverInfo(HttpSession session, HttpServletRequest request) {
		JSONObject callback = getCallbackResponse(request);
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", "Bearer " + callback.get("access_token"));
		String responseBody = get(apiURL, requestHeaders);
		try {
			NaverUserInfo info = new ObjectMapper().readValue(responseBody, NaverUserInfo.class);
			NaverAuthenticationToken token = new NaverAuthenticationToken(info.getResponse().getId(),
					info.getResponse().getNickname());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			/*session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
			          SecurityContextHolder.getContext());*/
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	private JSONObject getCallbackResponse(HttpServletRequest request) {
		HttpURLConnection con = null;
		BufferedReader br = null;
		try {
			String apiURL = getLoginCallbackUrl(request);
			con = connect(apiURL);

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			return (JSONObject) new JSONParser().parse(res.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (con != null) {
				con.disconnect();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
	}

	private String getLoginCallbackUrl(HttpServletRequest request) throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback", "UTF-8");
		StringBuilder apiURL = new StringBuilder();
		apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
		apiURL.append("client_id=");
		apiURL.append(clientId);
		apiURL.append("&client_secret=");
		apiURL.append(clientSecret);
		apiURL.append("&redirect_uri=");
		apiURL.append(redirectURI);
		apiURL.append("&code=");
		apiURL.append(code);
		apiURL.append("&state=");
		apiURL.append(state);
		return apiURL.toString();
	}
}
