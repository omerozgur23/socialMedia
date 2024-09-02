package com.socialMedia.business.rules.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.Status;
import com.socialMedia.entities.User;

@Service
public class LoginBusinessRules {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void checkUserPasswordMatch(User user, String password) {
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BusinessException(Messages.WRONG_PASSWORD);
		}
	}

	public void activateAccount(User user) {
		if (user.getStatus() != Status.ACTIVE) {
			user.setStatus(Status.ACTIVE);
			user.setDeletedDate(null);
			userRepository.save(user);
		}
	}

	public Optional<User> isUserExist(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new BusinessException(Messages.USER_NOT_FOUND);
		}
		return user;
	}

}
