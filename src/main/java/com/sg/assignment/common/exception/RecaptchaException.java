package com.sg.assignment.common.exception;

public class RecaptchaException extends AbstractException {

	private static final long serialVersionUID = -3772807562481343717L;

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return RECAPTCHA_ERR_CODE;
	}
}
