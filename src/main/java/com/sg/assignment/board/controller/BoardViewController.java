package com.sg.assignment.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.service.BoardService;
import com.sg.assignment.common.enums.PageType;
import com.sg.assignment.common.enums.SearchType;

@Controller
@RequestMapping("/boards")
public class BoardViewController {

	@Value("${google.recaptcha.site-key}")
	private String siteKey;

	@Autowired
	private BoardService boardService;

	@GetMapping
	public ModelAndView list(BoardCondition boardCondition, Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list/list");
		mav.addObject("types", SearchType.values());
		mav.addObject("loginId", authentication.getPrincipal().toString());
		return mav;
	}

	@GetMapping("/{id}")
	public ModelAndView detail(@PathVariable long id, Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/detail/detail");
		mav.addObject("siteKey", siteKey);
		mav.addObject("loginId", authentication.getPrincipal().toString());
		if (id > 0) {
			mav.addObject("data", boardService.getBoard(id));
			mav.addObject("pageType", PageType.info);
		} else {
			mav.addObject("pageType", PageType.create);
		}
		return mav;
	}

	@GetMapping("/{id}/update")
	public ModelAndView update(@PathVariable long id, Authentication authentication) {
		ModelAndView mav = detail(id, authentication);
		mav.addObject("pageType", PageType.update);
		return mav;
	}
}
