package com.socialMedia.dtos.signUp;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

	@Email(message = Messages.PLEASE_ENTER_A_VALID_EMAIL_ADDRESS)
	@NotBlank(message = Messages.EMAIL_CANNOT_BE_BLANK)
	private String email;

	@NotBlank(message = Messages.USERNAME_CANNOT_BE_BLANK)
	private String username;

	@NotBlank(message = Messages.NAME_CANNOT_BE_BLANK)
	private String name;

	@Size(max = 200, message = Messages.YOU_HAVE_EXISTIS_EXCEEDED_THE_CHARACTER_LIMIT_200)
	private String profilePhoto;

	@NotBlank(message = Messages.BIRTH_DATE_CANNOT_BE_BLANK)
	private String birthDate;

	private String password;

	@Size(max = 200, message = Messages.YOU_HAVE_EXISTIS_EXCEEDED_THE_CHARACTER_LIMIT_200)
	private String description;
}
