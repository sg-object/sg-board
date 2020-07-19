package com.sg.assignment.board.model;

import com.sg.assignment.common.enums.SearchType;
import lombok.Data;

@Data
public class BoardCondition {

	private int page;

	private int size;

	private SearchType type;

	private String keyword;
}
