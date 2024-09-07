package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.signUp.SignUpRequest;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@PostMapping("/signup")
	public SuccessResponse signUp(@RequestBody SignUpRequest request) {
		signUpService.signUp(request);
		return new SuccessResponse();
	}

	@GetMapping(path = "confirm")
	public SuccessResponse confirm(@RequestParam("token") String token) {
		signUpService.confirmAccount(token);
		return new SuccessResponse();
	}
}
