package com.socialMedia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.socialMedia.business.abstracts.ConfirmationTokenService;
import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.business.concretes.SignUpManager;
import com.socialMedia.business.rules.signUp.SignUpBusinessRules;
import com.socialMedia.core.utilities.config.mailSender.JavaMailSenderService;
import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.entities.User;

@SpringBootTest
public class SignUpManagerTest {

	@InjectMocks
	private SignUpManager signUpManager;

	@Mock
	private UserService userService;

	@Mock
	private SignUpBusinessRules signUpBusinessRules;

	@Mock
	ConfirmationTokenService confirmationTokenService;

	@Mock
	private JavaMailSenderService emailSenderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSignUp() {

		SignUpRequest request = new SignUpRequest();
		request.setEmail("omerozgur23@gmail.com");
		request.setUsername("@omerozgur");
		request.setName("Ömer Özgür");
		request.setBirthDate("23.06.1998");
		request.setPassword("Omer12345");

		User user = new User();
		String token = "testtoken";

		when(userService.create(request)).thenReturn(user);
		when(confirmationTokenService.create(user)).thenReturn(token);

		String result = signUpManager.signUp(request);

		assertEquals(token, result);
		verify(signUpBusinessRules).checkIfEmailExists(request.getEmail());
		verify(signUpBusinessRules).checkIfUsernameExists(request.getUsername());
		verify(signUpBusinessRules).validatePassword(request.getPassword());
		verify(userService).create(request);
		verify(confirmationTokenService).create(user);
		verify(emailSenderService).send(eq(request.getEmail()), anyString());
	}
}
