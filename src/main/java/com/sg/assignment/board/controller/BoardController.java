package com.sg.assignment.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sg.assignment.board.model.Board;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.service.BoardService;

@Controller
@RequestMapping("/notices")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping
	public ModelAndView list(BoardCondition boardCondition) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/main");
		mav.addObject("result", boardService.getBoardList(boardCondition));
		return mav;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Board getBoard(@PathVariable long id) {
		return boardService.getBoard(id);
	}

	@PostMapping
	public void createBoard(@RequestBody Board board) {
	}

	@PutMapping
	public void updateBoard(@RequestBody Board board) {
	}

	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable long id) {
	}
}
