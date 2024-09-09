package com.socialMedia.dtos.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReConfirmationTokenRequest {

	private String email;
}
