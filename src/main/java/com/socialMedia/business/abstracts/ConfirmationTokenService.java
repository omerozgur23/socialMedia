package com.socialMedia.business.abstracts;

import java.util.Optional;

import com.socialMedia.entities.ConfirmationToken;

public interface ConfirmationTokenService {

	void saveConfirmationToken(ConfirmationToken token);

	Optional<ConfirmationToken> getToken(String token);

	int setConfirmedAt(String token);
}
