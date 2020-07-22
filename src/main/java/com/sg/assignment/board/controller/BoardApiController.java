package com.sg.assignment.board.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sg.assignment.board.model.Board;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.model.BoardListResult;
import com.sg.assignment.board.service.BoardService;
import com.sg.assignment.common.service.RecaptchaService;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private RecaptchaService recaptchaService;

	@GetMapping
	public BoardListResult getBoardList(BoardCondition boardCondition) {
		return boardService.getBoardList(boardCondition);
	}

	@PostMapping
	public Long createBoard(@RequestBody Board board, Principal principal) {
		recaptchaService.checkRecaptchaResponse(board.getRecaptchaResponse());
		board.setUserId(principal.getName());
		boardService.createBoard(board);
		return board.getId();
	}

	@PutMapping("/{id}")
	public void updateBoard(@PathVariable long id, @RequestBody Board board, Principal principal) {
		recaptchaService.checkRecaptchaResponse(board.getRecaptchaResponse());
		board.setId(id);
		board.setUserId(principal.getName());
		boardService.updateBoard(board);
	}

	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable long id, Principal principal) {
		boardService.deleteBoard(id, principal.getName());
	}
}
