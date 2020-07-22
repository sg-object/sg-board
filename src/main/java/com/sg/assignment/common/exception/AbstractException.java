package com.sg.assignment.common.exception;

public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected final String VERIFICATION_CODE = "0001";

	protected final String DUP_DATA_CODE = "0002";

	protected final String NOT_FOUND_DATA_CODE = "0003";

	protected final String USING_DATA_CODE = "0004";

	protected final String RECAPTCHA_ERR_CODE = "0005";

	protected final String INVALID_USER_CODE = "0006";

	protected final String DUP_USER_CODE = "1001";

	protected static final String INTERNAL_SERVER_ERROR = "9999";

	public abstract String getErrorCode();
}
