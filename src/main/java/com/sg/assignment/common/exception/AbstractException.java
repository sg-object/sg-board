package com.sg.assignment.common.exception;

public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String VERIFICATION_CODE = "0001";

	public static final String DUP_DATA_CODE = "0002";

	public static final String NOT_FOUND_DATA_CODE = "0003";

	public static final String USING_DATA_CODE = "0004";

	public static final String INTERNAL_SERVER_ERROR = "9999";

	public abstract String getErrorCode();
}
