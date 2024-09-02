package com.socialMedia.business.concretes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.SignUpService;
import com.socialMedia.business.rules.signUp.SignUpBusinessRules;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dtos.signUp.SignUpRequest;
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

	@Override
	public User signUp(SignUpRequest request) {
		signUpBusinessRules.checkIfEmailExist(request.getEmail());
		signUpBusinessRules.checkIfUsernameExists(request.getUsername());

		User user = modelMapper.forRequest().map(request, User.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setBirthDate(signUpBusinessRules.formatterDate(request.getBirthDate()));
		user.setCreatedDate(LocalDateTime.now());
		user.setStatus(Status.ACTIVE);
		user.setProAccount(false);
		userRepository.save(user);
		return user;
	}

}
