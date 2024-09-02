package com.socialMedia.business.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.business.rules.user.UserBusinessRules;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.user.ChangePasswordRequest;
import com.socialMedia.dtos.user.GetAllUserResponse;
import com.socialMedia.dtos.user.UpdateUserRequest;
import com.socialMedia.entities.Status;
import com.socialMedia.entities.User;

import jakarta.transaction.Transactional;

@Service
public class UserManager implements UserService {

	@Autowired
	private UserBusinessRules userBusinessRules;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public PageResponse<GetAllUserResponse> getAll() {

		List<User> users = userRepository.findAll();
		List<GetAllUserResponse> response = users.stream()
				.map(user -> modelMapper.forResponse().map(user, GetAllUserResponse.class)).toList();
		int count = users.size();
		return new PageResponse<GetAllUserResponse>(count, response);
	}

	@Override
	public User update(UpdateUserRequest request) {

		User user = getUser(request.getId());

		userBusinessRules.checkEmailExistsForUpdate(request.getId(), request.getEmail());
		userBusinessRules.checkUsernameExists(request.getId(), request.getUsername());
		if (!request.getEmail().isEmpty())
			System.out.println("Doğrulama kodu gönderildi!");

		LocalDate birthDate = userBusinessRules.formatterDate(request.getBirthDate());
		user.setBirthDate(birthDate);

		BeanUtils.copyProperties(request, user, "id", "createdDate", "status", "birthDate");

		userRepository.save(user);

		return user;
	}

	@Override
	public void softDelete(String email) {
		User currentUser = userBusinessRules.checkAuthUserEmailMatch(email);
		currentUser.setStatus(Status.PASSIVE);
		currentUser.setDeletedDate(LocalDateTime.now());
		userRepository.save(currentUser);
	}

	@Transactional
	@Override
	@Scheduled(cron = "0 0 0 1 * ?")
	public void hardDelete() {
		LocalDateTime cutOffTime = LocalDateTime.now().minusDays(32);
		userRepository.hardDelete(cutOffTime);
	}

	@Override
	public void changePassword(ChangePasswordRequest request, String email) {
		Optional<User> user = userRepository.findByEmail(email);
		userBusinessRules.checkOldPasswordIsMatch(user.get(), request.getOldPassword());
		user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepository.save(user.get());
	}

	@Override
	public User getUser(UUID id) {
		Optional<User> oUser = userRepository.findById(id);
		User user = null;
		if (oUser.isPresent()) {
			user = oUser.get();
		} else {
			throw new BusinessException(Messages.USER_NOT_FOUND);
		}
		return user;
	}

}
