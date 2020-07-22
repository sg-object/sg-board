package com.sg.assignment.common.exception;

public class DuplicateUserException extends AbstractException {

	private static final long serialVersionUID = 2230009132442788372L;

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return DUP_USER_CODE;
	}
}
