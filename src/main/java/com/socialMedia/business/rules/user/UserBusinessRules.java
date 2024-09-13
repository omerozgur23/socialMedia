package com.socialMedia.business.rules.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.core.utilities.validation.pasword.PasswordValidator;
import com.socialMedia.core.utilities.validation.pasword.PasswordValidatorBuilder;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dtos.follower.RemoveFollowerUserRequest;
import com.socialMedia.dtos.follower.UnfollowUserRequest;
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

	public void validatePassword(String password) {
		PasswordValidator passwordValidator = new PasswordValidatorBuilder().withMinLength(8).withMaxLength(16)
				.withLowerCase().withUpperCase().withDigit().build();
		passwordValidator.validate(password);
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

	public void preventUserFromFollowingSelf(User currentUser, User followingUser) {
		if (currentUser.getId() == followingUser.getId())
			throw new BusinessException(Messages.USER_CANNOT_BE_FOLLOW_SELF);
	}

	public void preventDuplicateFollow(User currentUser, User followingUser) {
		for (User user : currentUser.getFollowings()) {
			if (user.getId().equals(followingUser.getId()))
				throw new BusinessException(Messages.USER_ALREADY_FOLLOWED);
		}
	}

	public void checkUserFollower(User currentUser, User followerUser) {
		List<User> users = currentUser.getFollowers();
		if (!users.contains(followerUser))
			throw new BusinessException(Messages.USER_NOT_FOLLOWED);
	}

	public void removeFollowerIfExists(User currentUser, User blocUser, UserService userService) {
		if (currentUser.getFollowers().contains(blocUser)) {
			RemoveFollowerUserRequest request = new RemoveFollowerUserRequest(blocUser.getId());
			userService.removeFollowerUser(request);
		}
	}

	public void unfollowIfFollowing(User currentUser, User blockUser, UserService userService) {
		if (currentUser.getFollowings().contains(blockUser)) {
			UnfollowUserRequest request = new UnfollowUserRequest(blockUser.getId());
			userService.unfollowUser(request);
		}
	}
}
