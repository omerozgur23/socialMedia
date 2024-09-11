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

import com.socialMedia.business.abstracts.ConfirmationTokenService;
import com.socialMedia.business.abstracts.TweetImagesService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.business.abstracts.TweetVideosService;
import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.business.abstracts.UserTweetService;
import com.socialMedia.business.rules.user.UserBusinessRules;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.core.utilities.config.mailSender.JavaMailSenderService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.follower.FollowUserRequest;
import com.socialMedia.dtos.follower.RemoveFollowerUserRequest;
import com.socialMedia.dtos.follower.UnfollowUserRequest;
import com.socialMedia.dtos.signUp.SignUpRequest;
import com.socialMedia.dtos.user.BlockUserRequest;
import com.socialMedia.dtos.user.ChangePasswordRequest;
import com.socialMedia.dtos.user.GetAllUserResponse;
import com.socialMedia.dtos.user.SuspendedUserRequest;
import com.socialMedia.dtos.user.UpdateUserRequest;
import com.socialMedia.entities.User;
import com.socialMedia.entities.enums.Status;

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

	@Autowired
	private UserTweetService userTweetService;

	@Autowired
	private TweetImagesService tweetImagesService;

	@Autowired
	private TweetVideosService tweetVideosService;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private JavaMailSenderService mailSender;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Override
	public PageResponse<GetAllUserResponse> getAll() {
		List<User> users = userRepository.findAll();
		List<GetAllUserResponse> response = users.stream()
				.map(user -> modelMapper.forResponse().map(user, GetAllUserResponse.class)).toList();
		int count = users.size();
		return new PageResponse<GetAllUserResponse>(count, response);
	}

	@Override
	public User create(SignUpRequest request) {
		User user = modelMapper.forRequest().map(request, User.class);
		user.setBirthDate(userBusinessRules.formatterDate(request.getBirthDate()));
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreatedDate(LocalDateTime.now());
		userRepository.save(user);
		return user;
	}

	@Override
	public User update(UpdateUserRequest request) {

		User user = getUser(request.getId());

		userBusinessRules.checkUsernameExists(request.getId(), request.getUsername());

		LocalDate birthDate = userBusinessRules.formatterDate(request.getBirthDate());
		user.setBirthDate(birthDate);

		if (!request.getEmail().isEmpty()) {
			userBusinessRules.checkEmailExistsForUpdate(request.getId(), request.getEmail());
			user.setStatus(Status.UNCONFIRMED);
			String token = confirmationTokenService.create(user);
			mailSender.send(request.getEmail(), mailSender.buildEmail(request.getName(), token));
		}

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

		List<UUID> passiveUsersId = userRepository.findByStatus(cutOffTime);
		List<UUID> userTweetsId = userTweetService.delete(passiveUsersId);

		tweetImagesService.delete(userTweetsId);
		tweetVideosService.delete(userTweetsId);
		tweetService.hardDelete(userTweetsId);

		userRepository.hardDelete(cutOffTime);
	}

	@Override
	public void suspendedAccount(SuspendedUserRequest request) {
		User user = getUser(request.getId());
		user.setStatus(Status.SUSPENDED);
		userRepository.save(user);
	}

	@Override
	public void changePassword(ChangePasswordRequest request, String email) {
		userBusinessRules.validatePassword(request.getNewPassword());
		Optional<User> user = userRepository.findByEmail(email);
		userBusinessRules.checkOldPasswordIsMatch(user.get(), request.getOldPassword());
		user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepository.save(user.get());
	}

	@Override
	public int enableUser(String email) {
		return userRepository.enableUser(email);
	}

	@Override
	public User getUser(UUID id) {
		return userRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
	}

	@Override
	public void followUser(FollowUserRequest request) {
		String currentUserEmail = AuthenticatedUser.getCurrentUser();
		User currentUser = userBusinessRules.getCurrentUser(currentUserEmail);
		User followingUser = getUser(request.getId());
		userBusinessRules.preventUserFromFollowingSelf(currentUser, followingUser);
		userBusinessRules.preventDuplicateFollow(currentUser, followingUser);

		currentUser.getFollowings().add(followingUser);
		followingUser.getFollowers().add(currentUser);

		userRepository.save(currentUser);
		userRepository.save(followingUser);
	}

	@Override
	public void unfollowUser(UnfollowUserRequest request) {
		String currentUserEmail = AuthenticatedUser.getCurrentUser();
		User currentUser = userBusinessRules.getCurrentUser(currentUserEmail);
		User unfollowUser = getUser(request.getId());
		currentUser.getFollowings().remove(unfollowUser);
		unfollowUser.getFollowers().remove(currentUser);

		userRepository.save(currentUser);
		userRepository.save(unfollowUser);
	}

	@Override
	public void removeFollowerUser(RemoveFollowerUserRequest request) {
		String currentUserEmail = AuthenticatedUser.getCurrentUser();
		User currentUser = userBusinessRules.getCurrentUser(currentUserEmail);
		User followerUser = getUser(request.getId());
		userBusinessRules.checkUserFollower(currentUser, followerUser);

		currentUser.getFollowers().remove(followerUser);
		followerUser.getFollowings().remove(currentUser);

		userRepository.save(currentUser);
		userRepository.save(followerUser);
	}

	@Transactional
	@Override
	public void blockUser(BlockUserRequest request) {
		String currentUserEmail = AuthenticatedUser.getCurrentUser();
		User currentUser = userBusinessRules.getCurrentUser(currentUserEmail);
		User blockUser = getUser(request.getId());

		userBusinessRules.removeFollowerIfExists(currentUser, blockUser, this);
		userBusinessRules.unfollowIfFollowing(currentUser, blockUser, this);

		currentUser.getBlockedUsers().add(blockUser);
		userRepository.save(currentUser);
	}
}
