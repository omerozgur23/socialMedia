package com.socialMedia.business.rules.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.User;

@Service
public class UserBusinessRules {

	@Autowired
	private UserRepository userRepository;

	public void checkIfEmailExist(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			throw new BusinessException(Messages.USER_EMAIL_ALREADY_EXISTS);
		}
	}

	public void checkIfUsernameExists(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			throw new BusinessException(Messages.USER_USERNAME_ALREADY_EXISTS);
		}
	}

	public Optional<User> isUserExist(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new BusinessException(Messages.USER_NOT_FOUND);
		}
		return user;
	}

	public LocalDate formatterDate(String request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate birthDate = LocalDate.parse(request, formatter);
		return birthDate;
	}

	public User checkAuthUserEmailMatch(String authenticatedEmail) {
		Optional<User> currentUser = userRepository.findByEmail(authenticatedEmail);
		if (currentUser.get().getEmail().equals(authenticatedEmail)) {
			return currentUser.get();
		} else {
			throw new BusinessException(Messages.AUTHENTICATED_USER_NOT_FOUND);
		}
	}

}
