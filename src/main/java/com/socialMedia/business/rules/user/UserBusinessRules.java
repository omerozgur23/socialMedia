package com.socialMedia.business.rules.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.User;

@Service
public class UserBusinessRules {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void checkEmailExistsForUpdate(UUID currentUserId, String email) {
		userRepository.findById(currentUserId).orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));

		userRepository.findByEmail(email).filter(user -> !user.getId().equals(currentUserId)).ifPresent(user -> {
			throw new BusinessException(Messages.USER_EMAIL_ALREADY_EXISTS);
		});
		;
	}

	public void checkUsernameExists(UUID currentUserId, String username) {
		userRepository.findById(currentUserId).orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));

		userRepository.findByUsername(username).filter(user -> !user.getId().equals(currentUserId)).ifPresent(user -> {
			throw new BusinessException(Messages.USER_EMAIL_ALREADY_EXISTS);
		});
		;
	}

	public void checkOldPasswordIsMatch(User user, String oldPassword) {
		if (!passwordEncoder.matches(oldPassword, user.getPassword()))
			throw new BusinessException(Messages.OLD_PASSWORD_NOT_MATCH);
	}

	public LocalDate formatterDate(String request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate birthDate = LocalDate.parse(request, formatter);
		return birthDate;
	}

	public User checkAuthUserEmailMatch(String authenticatedEmail) {
		return userRepository.findByEmail(authenticatedEmail)
				.orElseThrow(() -> new BusinessException(Messages.AUTHENTICATED_USER_NOT_FOUND));
	}

	public User getCurrentUser(String currentUserEmail) {
		return userRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
	}

}
