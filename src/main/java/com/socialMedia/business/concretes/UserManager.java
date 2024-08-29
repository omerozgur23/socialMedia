package com.socialMedia.business.concretes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.business.rules.user.UserBusinessRules;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.Status;
import com.socialMedia.entities.User;

import jakarta.transaction.Transactional;

@Service
public class UserManager implements UserService {

	@Autowired
	private UserBusinessRules userBusinessRules;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void softDelete() {
		String authenticatedEmail = AuthenticatedUser.getCurrentUser();
		User currentUser = userBusinessRules.checkAuthUserEmailMatch(authenticatedEmail);
		currentUser.setStatus(Status.PASSIVE);
		currentUser.setDeletedDate(LocalDateTime.now());
		userRepository.save(currentUser);
	}

	@Transactional
	@Override
	@Scheduled(cron = "0 0 0 1 * ?")
	public void hardDelete() {
		LocalDateTime cutOffTime = LocalDateTime.now().minusDays(32);
		userRepository.hardDelete(cutOffTime);
	}

}
