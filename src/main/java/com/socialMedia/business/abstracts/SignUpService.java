package com.socialMedia.business.abstracts;

import com.socialMedia.core.utilities.results.Result;
import com.socialMedia.dtos.signUp.ReConfirmationTokenRequest;
import com.socialMedia.dtos.signUp.SignUpRequest;

public interface SignUpService {

	String signUp(SignUpRequest request);

	Result confirmAccount(String token);

	void sendReConfirmationToken(ReConfirmationTokenRequest request);
}
