package com.sg.assignment.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sg.assignment.common.service.NaverService;
import com.sg.assignment.user.model.User;
import com.sg.assignment.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private NaverService naverService;

	@Autowired
	private UserService userService;

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join")
	public void join(@RequestBody User user) {
		userService.join(user);
	}

	@GetMapping("/")
	public ModelAndView login(HttpSession session, @RequestParam(value = "error", required = false) String error) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login");
		mav.addObject("apiURL", naverService.getLoginApiUrl(session));
		if (error != null) {
			mav.addObject("errorMessage", "로그인에 실패 했습니다.\n아이디와 비밀번호를 확인해주세요.");
		}
		return mav;
	}

	@GetMapping("/naver/callback")
	public String naverCallback(HttpSession session, HttpServletRequest request) {
		naverService.applyNaverInfo(session, request);
		return "redirect:/boards";
	}
}
