package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.RegistrationService;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.signUp.SignUpRequest;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/signup")
	public SuccessResponse signUp(@RequestBody SignUpRequest request) {
		registrationService.register(request);

		return new SuccessResponse();
	}

	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
}
