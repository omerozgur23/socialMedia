package com.socialMedia.core.utilities.validation.pasword;

import com.socialMedia.core.utilities.exceptions.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinLengthValidationRule implements ValidationRule {

	private final int minLength;

	@Override
	public void validate(String password) {
		if (password == null || password.length() < minLength)
			throw new BusinessException("Password must be at least " + minLength + " characters long");
	}
}
