package com.socialMedia.dtos.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {

	private UUID id;

	private String email;

	private String username;

	private String name;

	private String profilePhoto;

	private String birthDate;

	private String description;

}
