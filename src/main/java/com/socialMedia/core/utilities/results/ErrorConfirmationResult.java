package com.socialMedia.core.utilities.results;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorConfirmationResult extends Result {

	private String message;
	
	public ErrorConfirmationResult(String redirectUrl, String message) {
		super(false, redirectUrl);
		this.message = message;
	}
	
	public ErrorConfirmationResult(String redirectUrl) {
		super(false, redirectUrl);
	}
	
}
