package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.entities.User;
import com.socialMedia.entities.auth.ConfirmationToken;

public interface ConfirmationTokenService {

	String create(User user);

	ConfirmationToken confirmToken(String token);

	ConfirmationToken getToken(String token);

	void delete(); 
}
