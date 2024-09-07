package com.socialMedia.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.LoginService;
import com.socialMedia.business.rules.login.LoginBusinessRules;
import com.socialMedia.dtos.login.LoginRequest;
import com.socialMedia.entities.User;

@Service
public class LoginManager implements LoginService {

	@Autowired
	private LoginBusinessRules loginBusinessRules;

	@Override
	public String login(LoginRequest request) {
		User user = loginBusinessRules.isUserExist(request.getEmail());
		loginBusinessRules.checkUserPasswordMatch(user, request.getPassword());
		return loginBusinessRules.generateTokenBasedOnStatus(user);
	}
}
