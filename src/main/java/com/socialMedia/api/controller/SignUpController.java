package com.socialMedia.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.core.utilities.results.Result;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.signUp.ReConfirmationTokenRequest;
import com.socialMedia.dtos.signUp.SignUpRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@PostMapping("/signup")
	public SuccessResponse signUp(@Valid @RequestBody SignUpRequest request) {
		signUpService.signUp(request);
		return new SuccessResponse();
	}

	@GetMapping(path = "confirm")
	public ResponseEntity<Void> confirmAccount(@RequestParam("token") String token) {
		Result result = signUpService.confirmAccount(token);
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(result.getRedirectUrl())).build();
	}

	@PostMapping("/resignup")
	public SuccessResponse sendReConfirmationToken(@Valid @RequestBody ReConfirmationTokenRequest request) {
		signUpService.sendReConfirmationToken(request);
		return new SuccessResponse();
	}
}
