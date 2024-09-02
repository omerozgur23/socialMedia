package com.socialMedia.business.rules.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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

	public void checkEmailExistsForUpdate(UUID id, String email) {

		Optional<User> currentUser = userRepository.findById(id);

		if (currentUser.isPresent()) {
			Optional<User> user = userRepository.findByEmail(email).filter(u -> !u.getId().equals(id));
			if (user.isPresent())
				throw new BusinessException(Messages.USER_EMAIL_ALREADY_EXISTS);
		} else {
			throw new BusinessException(Messages.USER_NOT_FOUND);
		}
	}

	public void checkUsernameExists(UUID id, String username) {

		Optional<User> currentUser = userRepository.findById(id);

		if (currentUser.isPresent()) {
			Optional<User> user = userRepository.findByUsername(username).filter(u -> !u.getId().equals(id));
			if (user.isPresent())
				throw new BusinessException(Messages.USER_USERNAME_ALREADY_EXISTS);
		} else {
			throw new BusinessException(Messages.USER_NOT_FOUND);
		}
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
		Optional<User> currentUser = userRepository.findByEmail(authenticatedEmail);
		if (currentUser.isPresent())
			return currentUser.get();
		else {
			throw new BusinessException(Messages.AUTHENTICATED_USER_NOT_FOUND);
		}
	}

}
