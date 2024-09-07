package com.socialMedia.business.concretes;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.ConfirmationTokenService;
import com.socialMedia.business.rules.confirmationToken.ConfirmationTokenRules;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.ConfirmationTokenRepository;
import com.socialMedia.entities.User;
import com.socialMedia.entities.auth.ConfirmationToken;

@Service
public class ConfirmationTokenManager implements ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private ConfirmationTokenRules confirmationTokenRules;

	@Override
	public String create(User user) {
		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = ConfirmationToken.builder().token(token).createdAt(LocalDateTime.now())
				.expiresAt(LocalDateTime.now().plusMinutes(15)).user(user).build();

		confirmationTokenRepository.save(confirmationToken);
		return token;
	}

	@Override
	public ConfirmationToken confirmToken(String token) {
		ConfirmationToken confirmationToken = getToken(token);
		confirmationTokenRules.checkIfTokenConfirmed(confirmationToken);
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		confirmationTokenRules.checkIfTokenExpired(expiredAt);
		confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
		return confirmationToken;
	}

	@Override
	public ConfirmationToken getToken(String token) {
		return confirmationTokenRepository.findByToken(token)
				.orElseThrow(() -> new BusinessException(Messages.TOKEN_NOT_FOUND));
	}
}
