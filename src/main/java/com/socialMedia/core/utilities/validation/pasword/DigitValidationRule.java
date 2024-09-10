package com.socialMedia.core.utilities.validation.pasword;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;

public class DigitValidationRule implements ValidationRule {

	@Override
	public void validate(String password) {
		if (!password.matches(".*\\d.*"))
			throw new BusinessException(Messages.PASSWORD_MUST_CONTAIN_AT_LEAST_ONE_NUMBER);
	}
}
