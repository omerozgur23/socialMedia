package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.LoginService;
import com.socialMedia.dtos.login.LoginRequest;
import com.socialMedia.dtos.login.LoginResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class LoginContrroller {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		String token = loginService.login(request);
		return new LoginResponse(token);
	}
}
