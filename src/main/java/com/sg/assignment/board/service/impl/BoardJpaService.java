package com.sg.assignment.board.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sg.assignment.board.model.Board;
import com.sg.assignment.board.model.BoardCondition;
import com.sg.assignment.board.model.BoardListResult;
import com.sg.assignment.board.repository.BoardRepository;
import com.sg.assignment.board.service.BoardService;
import com.sg.assignment.common.enums.SearchType;
import com.sg.assignment.common.exception.InvalidUserException;
import com.sg.assignment.common.exception.NotFoundDataException;
import com.sg.assignment.common.util.VerificationUtils;

@Service
public class BoardJpaService implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@PostConstruct
	private void init() {
		List<Board> list = new ArrayList<Board>();
		String title = "title_";
		String con = "con_";
		String id = "test";
		for (int i = 1; i <= 200; i++) {
			Board b = new Board();
			b.setTitle(title + i);
			b.setContent(con + i);
			b.setUserId(id);
			list.add(b);
		}
		boardRepository.saveAll(list);
	}

	@Override
	public BoardListResult getBoardList(BoardCondition boardCondition) {
		// TODO Auto-generated method stub
		// 검색 조건 검사
		boardCondition.checkCondition();
		if (VerificationUtils.isNullOrBlank(boardCondition.getKeyword())) {
			return getBoardListResult(boardRepository.findAllByDeleteYn(false, getPageRequest(boardCondition)));
		} else {
			if (SearchType.title.equals(boardCondition.getType())) {
				return getBoardListResult(boardRepository.findAllByDeleteYnAndTitleContaining(false,
						boardCondition.getKeyword(), getPageRequest(boardCondition)));
			} else {
				return getBoardListResult(boardRepository.findAllByDeleteYnAndUserIdContaining(false,
						boardCondition.getKeyword(), getPageRequest(boardCondition)));
			}
		}
	}

	@Override
	public Board getBoard(long id) {
		// TODO Auto-generated method stub
		Optional<Board> optional = boardRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotFoundDataException();
		}
	}

	@Transactional
	@Override
	public void createBoard(Board board) {
		// TODO Auto-generated method stub
		board.checkValue();
		boardRepository.save(board);
	}

	@Transactional
	@Override
	public void updateBoard(Board board) {
		// TODO Auto-generated method stub
		board.checkValue();
		Board data = getBoard(board.getId());
		if (!board.getUserId().equals(data.getUserId())) {
			throw new InvalidUserException();
		}
		data.setTitle(board.getTitle());
		data.setContent(board.getContent());
	}

	@Transactional
	@Override
	public void deleteBoard(long id, String loginId) {
		// TODO Auto-generated method stub
		Board data = getBoard(id);
		if (!loginId.equals(data.getUserId())) {
			throw new InvalidUserException();
		}
		data.setDeleteYn(true);
	}

	private BoardListResult getBoardListResult(Page<Board> page) {
		BoardListResult result = new BoardListResult();
		result.setData(page.getContent());
		result.setRecordsTotal(page.getTotalElements());
		result.setRecordsFiltered(result.getRecordsTotal());
		return result;
	}

	private PageRequest getPageRequest(BoardCondition boardCondition) {
		return PageRequest.of((boardCondition.getStart() / boardCondition.getLength()), boardCondition.getLength(),
				Sort.by("id").descending());
	}
}
