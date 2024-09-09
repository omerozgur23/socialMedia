package com.socialMedia.business.abstracts;

import com.socialMedia.core.utilities.results.ConfirmationResult;
import com.socialMedia.dtos.signUp.ReConfirmationTokenRequest;
import com.socialMedia.dtos.signUp.SignUpRequest;

public interface SignUpService {

	String signUp(SignUpRequest request);

	ConfirmationResult confirmAccount(String token);

	void sendReConfirmationToken(ReConfirmationTokenRequest request);
}
