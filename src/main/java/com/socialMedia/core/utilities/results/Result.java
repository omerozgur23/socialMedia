package com.socialMedia.core.utilities.results;

import lombok.Data;

@Data
public class Result {

	private boolean success;
	
	private final String frontendUrl = "http://localhost:4040";
	
	private String redirectUrl;
	
	public Result (boolean success, String redirectUrl) {
		this.success = success;
		this.redirectUrl = frontendUrl + redirectUrl;
	}
}
