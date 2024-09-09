package com.socialMedia.business.rules.signUp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.core.utilities.results.ErrorConfirmationResult;
import com.socialMedia.core.utilities.results.Result;
import com.socialMedia.core.utilities.results.SuccessConfirmationResult;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.User;
import com.socialMedia.entities.auth.ConfirmationToken;
import com.socialMedia.entities.enums.Status;

@Service
public class SignUpBusinessRules {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	public void checkIfEmailExists(String email) {
		userRepository.findByEmail(email).ifPresent(user -> {
			if (user.getStatus().equals(Status.UNCONFIRMED)) {
				throw new BusinessException(Messages.ACCOUNT_IS_UNCONFIRMED);
			} else {
				throw new BusinessException(Messages.USER_EMAIL_ALREADY_EXISTS);
			}
		});
	}

	public void checkIfUsernameExists(String username) {
		userRepository.findByUsername(username).ifPresent(user -> {
			throw new BusinessException(Messages.USER_USERNAME_ALREADY_EXISTS);
		});
	}

	public User checkIfEmailExistsForReToken(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException(Messages.NO_ACCOUNT_FOUND));
	}

	public LocalDate formatterDate(String request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate birthDate = LocalDate.parse(request, formatter);
		return birthDate;
	}

	public Result checkConfirmationToken(ConfirmationToken confirmationToken) {
		
		if(confirmationToken != null && !confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			userService.enableUser(confirmationToken.getUser().getEmail());
			return new SuccessConfirmationResult("/home");
		}
		else if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			return new ErrorConfirmationResult("/resignup", Messages.CONFIRMATION_MAIL_HAS_EXPIRED);
		}
		return new ErrorConfirmationResult("/error");
	}
	
}
