package com.socialMedia.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.ConfirmationTokenService;
import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.business.rules.signUp.SignUpBusinessRules;
import com.socialMedia.core.utilities.config.mailSender.JavaMailSenderService;
import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.entities.User;
import com.socialMedia.entities.auth.ConfirmationToken;

import jakarta.transaction.Transactional;

@Service
public class SignUpManager implements SignUpService {

	@Autowired
	private UserService userService;

	@Autowired
	private SignUpBusinessRules signUpBusinessRules;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private JavaMailSenderService emailSenderService;

	@Transactional
	@Override
	public String signUp(SignUpRequest request) {
		signUpBusinessRules.checkIfEmailExist(request.getEmail());
		signUpBusinessRules.checkIfUsernameExists(request.getUsername());

		User user = userService.create(request);
		String token = confirmationTokenService.create(user);

		emailSenderService.send(request.getEmail(), emailSenderService.buildEmail(request.getName(), token));
		return token;
	}

	@Override
	public String confirmAccount(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.confirmToken(token);
		userService.enableUser(confirmationToken.getUser().getEmail());
		return token;
	}

}
