package com.sg.assignment.common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageRequestUtils {

	public static PageRequest getRequest(int page, int size, Sort sort) {
		return PageRequest.of(page - 1, size, sort);
	}

	public static PageRequest getRequest(int page, int size) {
		return PageRequest.of(page - 1, size);
	}
}
