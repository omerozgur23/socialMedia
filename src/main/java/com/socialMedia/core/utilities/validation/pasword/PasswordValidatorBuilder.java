package com.socialMedia.core.utilities.validation.pasword;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidatorBuilder {

	private final List<ValidationRule> rules = new ArrayList<>();

	public PasswordValidatorBuilder withMinLength(int minLength) {
		rules.add(new MinLengthValidationRule(minLength));
		return this;
	}

	public PasswordValidatorBuilder withMaxLength(int maxLength) {
		rules.add(new MaxLengthValidationRule(maxLength));
		return this;
	}

	public PasswordValidatorBuilder withLowerCase() {
		rules.add(new LowerCaseValidationRule());
		return this;
	}

	public PasswordValidatorBuilder withUpperCase() {
		rules.add(new UpperCaseValidationRule());
		return this;
	}

	public PasswordValidatorBuilder withDigit() {
		rules.add(new DigitValidationRule());
		return this;
	}

	public PasswordValidator build() {
		return new PasswordValidator(rules);
	}
}
