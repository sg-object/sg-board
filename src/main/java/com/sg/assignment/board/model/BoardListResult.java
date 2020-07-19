package com.sg.assignment.board.model;

import java.util.List;
import lombok.Data;

@Data
public class BoardListResult {

	private List<Board> boardList;

	private long totalCount;

	private int totalPageCount;

	private int page;

	private int size;
}
