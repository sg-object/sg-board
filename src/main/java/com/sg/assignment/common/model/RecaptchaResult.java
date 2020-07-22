package com.sg.assignment.common.model;

import lombok.Data;

@Data
public class RecaptchaResult {

	private boolean success;

	private String hostname;
}
