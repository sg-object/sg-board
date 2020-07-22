package com.sg.assignment.board.model;

import java.util.List;
import lombok.Data;

@Data
public class BoardListResult {

	private List<Board> data;

	private long recordsTotal;

	private long recordsFiltered;

	private int page;

	private int size;
}
