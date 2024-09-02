package com.socialMedia.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.LoginService;
import com.socialMedia.business.rules.login.LoginBusinessRules;
import com.socialMedia.core.utilities.config.jwt.JwtConfig;
import com.socialMedia.dtos.login.LoginRequest;
import com.socialMedia.entities.User;

@Service
public class LoginManager implements LoginService {

	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private LoginBusinessRules loginBusinessRules;

	@Override
	public String login(LoginRequest request) {
		Optional<User> user = loginBusinessRules.isUserExist(request.getEmail());
		loginBusinessRules.checkUserPasswordMatch(user.get(), request.getPassword());
		loginBusinessRules.activateAccount(user.get());
		String token = jwtConfig.createToken(user.get());
		return token;
	}

}
