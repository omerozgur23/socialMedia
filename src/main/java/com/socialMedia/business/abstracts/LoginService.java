package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.login.LoginRequest;

public interface LoginService {

	String login(LoginRequest request);
}
