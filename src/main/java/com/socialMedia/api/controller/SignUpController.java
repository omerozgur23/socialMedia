package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.dataAccess.TokenRepository;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.signUp.RegistrationCompleteEvent;
import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.entities.User;
import com.socialMedia.entities.VerificationToken;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private SignUpService signUpService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public SuccessResponse signUp(@RequestBody SignUpRequest request, final HttpServletRequest httpServlet) {

		User user = signUpService.signUp(request);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServlet)));
		return new SuccessResponse();
	}

	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token) {
		VerificationToken theToken = tokenRepository.findByToken(token);

		if (theToken.getUser().isEnabled()) {
			return "Your email address has already been verified, please log in.";
		}
		String verificationResult = userService.validateToken(token);

		if (verificationResult.equalsIgnoreCase("valid")) {
			return "Email verification successful! Now you can log in to your account.";
		}
		return "Your email verification link is invalid or has expired. Please, sign up again.";
	}

	public String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
