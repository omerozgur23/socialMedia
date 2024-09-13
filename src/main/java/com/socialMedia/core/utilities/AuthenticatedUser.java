package com.socialMedia.core.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.User;

import jakarta.annotation.PostConstruct;

@Service
public class AuthenticatedUser {

	@Autowired
	private UserRepository userRepositoryInstance;

	private static UserRepository userRepository;

	@PostConstruct
	public void init() {
		userRepository = this.userRepositoryInstance;
	}

	public static String getCurrentUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return authentication.getName();
		}
		return "unknown";
	}

	public static User getCurrentUser() {
		String currentUserEmail = getCurrentUserEmail();
		return userRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
	}
}
