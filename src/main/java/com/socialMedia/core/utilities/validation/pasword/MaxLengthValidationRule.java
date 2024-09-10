package com.socialMedia.core.utilities.validation.pasword;

import com.socialMedia.core.utilities.exceptions.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaxLengthValidationRule implements ValidationRule {

	private final int maxLength;

	@Override
	public void validate(String password) {
		if (password == null || password.length() > maxLength)
			throw new BusinessException("Password must be at most " + maxLength + " characters long");
	}

}
