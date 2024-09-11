package com.socialMedia.dtos.user;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

	@NotBlank(message = Messages.PASSWORD_CANNOT_BE_BLANK)
	private String oldPassword;

	@NotBlank(message = Messages.PASSWORD_CANNOT_BE_BLANK)
	private String newPassword;
}
