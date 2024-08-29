package com.socialMedia.dtos.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

	private String email;

	private String username;

	private String name;

	private String profilePhoto;

	private String birthDate;

	private String password;

	private String description;
}
