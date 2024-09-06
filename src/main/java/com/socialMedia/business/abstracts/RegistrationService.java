package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.signUp.SignUpRequest;

public interface RegistrationService {
	String register(SignUpRequest request);

	String confirmToken(String token);

	String buildEmail(String name, String link);
}
