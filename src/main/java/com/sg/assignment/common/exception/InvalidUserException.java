package com.sg.assignment.common.exception;

public class InvalidUserException extends AbstractException {

	private static final long serialVersionUID = -7537204630780202406L;

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return INVALID_USER_CODE;
	}
}
