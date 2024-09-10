package com.socialMedia.dtos.login;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@Email(message = Messages.PLEASE_ENTER_A_VALID_EMAIL_ADDRESS)
	@NotBlank(message = Messages.EMAIL_CANNOT_BE_BLANK)
	private String email;

	@NotBlank(message = Messages.PASSWORD_CANNOT_BE_BLANK)
	private String password;
}
