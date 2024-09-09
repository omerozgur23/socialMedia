package com.socialMedia.core.utilities.results;

import lombok.EqualsAndHashCode;

//@Data
@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
public class ConfirmationResult extends Result {

	private final boolean success;

	private final String redirectUrl;

	public ConfirmationResult(String frontendUrl, boolean success, String redirectUrl) {
		super(frontendUrl);
		this.success = success;
		this.redirectUrl = redirectUrl;
	}

}
