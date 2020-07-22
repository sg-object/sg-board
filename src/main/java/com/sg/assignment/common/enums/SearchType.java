package com.sg.assignment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SearchType {

	title("제목"), writer("작성자");

	@Getter
	private String name;
}
