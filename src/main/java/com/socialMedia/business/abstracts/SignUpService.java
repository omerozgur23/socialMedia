package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.signUp.SignUpRequest;

public interface SignUpService {

	String signUp(SignUpRequest request);

	String confirmAccount(String token);
}
