package com.socialMedia.business.abstracts;

import com.socialMedia.entities.User;

public interface SignUpService {

	String signUp(User request);

	int enableUser(String email);

}
