package com.socialMedia.business.rules.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.core.utilities.config.jwt.JwtConfig;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.User;
import com.socialMedia.entities.enums.Status;

@Service
public class LoginBusinessRules {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtConfig jwtConfig;

	public void checkUserPasswordMatch(User user, String password) {
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BusinessException(Messages.WRONG_PASSWORD);
		}
	}

	public User isUserExist(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
	}

	public String generateTokenBasedOnStatus(User user) {
		if (user.getStatus() == Status.ACTIVE)
			return jwtConfig.createToken(user);

		if (user.getStatus() == Status.PASSIVE || user.getStatus() == Status.SUSPENDED) {
			userService.enableUser(user.getEmail());
			return jwtConfig.createToken(user);
		}

		throw new BusinessException(Messages.UNSUPPORTED_USER_STATUS + user.getStatus());
	}

}
