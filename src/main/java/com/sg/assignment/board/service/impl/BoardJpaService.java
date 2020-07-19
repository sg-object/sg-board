package com.sg.assignment.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sg.assignment.board.model.Board;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.model.BoardListResult;
import com.sg.assignment.board.repository.BoardRepository;
import com.sg.assignment.board.service.BoardService;
import com.sg.assignment.common.enums.SearchType;
import com.sg.assignment.common.util.PageRequestUtils;
import com.sg.assignment.common.util.VerificationUtils;

@Service
public class BoardJpaService implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public BoardListResult getBoardList(BoardCondition boardCondition) {
		// TODO Auto-generated method stub
		if (VerificationUtils.isNullOrBlank(boardCondition.getKeyword())) {
			return getBoardListResult(boardRepository.findAll(getPageRequest(boardCondition)));
		} else {
			if (boardCondition.getType() == null) {
				// error
			}

			if (SearchType.title.equals(boardCondition.getType())) {
				return getBoardListResult(boardRepository.findAllByTitleContaining(boardCondition.getKeyword(),
						getPageRequest(boardCondition)));
			} else {
				return getBoardListResult(boardRepository.findAllByUserIdContaining(boardCondition.getKeyword(),
						getPageRequest(boardCondition)));
			}
		}
	}

	@Override
	public Board getBoard(long id) {
		// TODO Auto-generated method stub
		return boardRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}

	private BoardListResult getBoardListResult(Page<Board> page) {
		BoardListResult result = new BoardListResult();
		result.setBoardList(page.getContent());
		result.setTotalCount(page.getTotalElements());
		result.setTotalPageCount(page.getTotalPages());
		return result;
	}

	private PageRequest getPageRequest(BoardCondition boardCondition) {
		return PageRequestUtils.getRequest(boardCondition.getPage(), boardCondition.getSize(),
				Sort.by("id").descending());
	}
}
