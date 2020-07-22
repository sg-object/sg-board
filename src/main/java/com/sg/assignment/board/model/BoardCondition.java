package com.sg.assignment.board.model;

import com.sg.assignment.common.enums.SearchType;
import com.sg.assignment.common.exception.VerificationException;
import com.sg.assignment.common.util.VerificationUtils;
import lombok.Data;

@Data
public class BoardCondition {

	private int start;

	private int length;

	private SearchType type;

	private String keyword;

	public void checkCondition() {
		if (this.start < 0 || this.length < 1) {
			throw new VerificationException();
		} else {
			if (!VerificationUtils.isNullOrBlank(this.keyword) && this.type == null) {
				throw new VerificationException();
			}
		}
	}
}
