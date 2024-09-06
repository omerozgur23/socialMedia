package com.socialMedia.business.concretes;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.ConfirmationTokenService;
import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.business.rules.signUp.SignUpBusinessRules;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.entities.ConfirmationToken;
import com.socialMedia.entities.Status;
import com.socialMedia.entities.User;

@Service
public class SignUpManager implements SignUpService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SignUpBusinessRules signUpBusinessRules;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapperService modelMapper;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Override
	public String signUp(User request) {
		signUpBusinessRules.checkIfEmailExist(request.getEmail());
		signUpBusinessRules.checkIfUsernameExists(request.getUsername());

		User user = modelMapper.forRequest().map(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setBirthDate(request.getBirthDate());
		user.setCreatedDate(LocalDateTime.now());
		user.setStatus(Status.ACTIVE);
		user.setProAccount(false);
		user.setEnabled(false);
		userRepository.save(user);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), user);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

//      TODO: SEND EMAIL

		return token;
	}

	@Override
	public int enableUser(String email) {
		return userRepository.enableUser(email);
	}
}
