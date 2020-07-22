package com.sg.assignment.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NaverUserInfo {

	private String resultcode;

	private String message;

	private NaverResponse response;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	public class NaverResponse {

		private String id;

		private String nickname;

		private String email;

		private String name;
	}
}
