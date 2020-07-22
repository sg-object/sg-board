package com.sg.assignment.common.exception;

public class NotFoundDataException extends AbstractException {

	private static final long serialVersionUID = -1487756032642716963L;

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return NOT_FOUND_DATA_CODE;
	}
}
