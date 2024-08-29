package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.entities.User;

public interface SignUpService {

	User signUp(SignUpRequest request);
}
