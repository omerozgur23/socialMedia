package com.socialMedia.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.core.utilities.results.ConfirmationResult;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.signUp.ReConfirmationTokenRequest;
import com.socialMedia.dtos.signUp.SignUpRequest;

import jakarta.servlet.http.HttpServletResponse;

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
	public void confirm(@RequestParam("token") String token, HttpServletResponse response) {
		try {
			ConfirmationResult result = signUpService.confirmAccount(token);
			response.sendRedirect(result.getRedirectUrl());
		} catch (Exception e) {
			e.printStackTrace(); // Hatanın detaylarını loglamak için bu önemli olabilir.
			// Yönlendirme sırasında hata olması durumunda kullanıcıyı bir hata sayfasına
			// yönlendirin
			try {
				response.sendRedirect("/error?message=" + e.getMessage());
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

//		return new SuccessResponse();
	}

	@PostMapping("/resignup")
	public SuccessResponse sendReConfirmationToken(@RequestBody ReConfirmationTokenRequest request) {
		signUpService.sendReConfirmationToken(request);
		return new SuccessResponse();
	}
}
