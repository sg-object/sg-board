package com.sg.assignment.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.sg.assignment.common.exception.RecaptchaException;
import com.sg.assignment.common.model.RecaptchaResult;
import com.sg.assignment.common.util.VerificationUtils;

@Service
public class RecaptchaService {

	@Value("${google.recaptcha.secret-key}")
	private String secretKey;

	@Value("${google.recaptcha.siteverify-url}")
	private String url;

	public void checkRecaptchaResponse(String response) {
		if (VerificationUtils.isNullOrBlank(response)) {
			throw new RecaptchaException();
		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKey);
		map.add("response", response);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		RecaptchaResult result = restTemplate.postForObject(url, request, RecaptchaResult.class);
		if (!result.isSuccess()) {
			throw new RecaptchaException();
		}
	}
}
