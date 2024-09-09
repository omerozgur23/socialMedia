package com.socialMedia.core.utilities.results;

public class SuccessConfirmationResult extends Result {

	public SuccessConfirmationResult(String redirectUrl) {
		super(true, redirectUrl);
	}
}
