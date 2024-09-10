package com.socialMedia.core.utilities.validation.pasword;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

	private final List<ValidationRule> validationRules = new ArrayList<>();

	public PasswordValidator(List<ValidationRule> rules) {
		this.validationRules.addAll(rules);
	}

	public void validate(String password) {
		for (ValidationRule rule : validationRules) {
			rule.validate(password);
		}
	}
}
