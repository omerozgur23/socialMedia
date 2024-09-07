package com.socialMedia.business.rules.confirmationToken;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.entities.auth.ConfirmationToken;

@Service
public class ConfirmationTokenRules {

	public void checkIfTokenConfirmed(ConfirmationToken token) {
		if (token.getConfirmedAt() != null)
			throw new BusinessException(Messages.EMAIL_ALREADY_CONFIRMED);
	}

	public void checkIfTokenExpired(LocalDateTime expiredAt) {
		if (expiredAt.isBefore(LocalDateTime.now()))
			throw new BusinessException(Messages.TOKEN_EXPIRED);
	}
}
