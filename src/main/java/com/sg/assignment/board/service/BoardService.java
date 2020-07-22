package com.sg.assignment.board.service;

import com.sg.assignment.board.model.Board;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.model.BoardListResult;

public interface BoardService {

	public BoardListResult getBoardList(BoardCondition boardCondition);

	public Board getBoard(long id);

	public void createBoard(Board board);

	public void updateBoard(Board board);

	public void deleteBoard(long id, String loginId);
}
