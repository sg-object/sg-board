package com.sg.assignment.common.util;

public class VerificationUtils {

	public static boolean isNullOrBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

	public static boolean checkPageValue(int page, int size) {
		if (page < 1 || size < 1) {
			return false;
		} else {
			return true;
		}
	}
}
