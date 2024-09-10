package com.socialMedia.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.follower.FollowUserRequest;
import com.socialMedia.dtos.follower.RemoveFollowerUserRequest;
import com.socialMedia.dtos.follower.UnfollowUserRequest;
import com.socialMedia.dtos.user.BlockUserRequest;
import com.socialMedia.dtos.user.ChangePasswordRequest;
import com.socialMedia.dtos.user.GetAllUserResponse;
import com.socialMedia.dtos.user.SuspendedUserRequest;
import com.socialMedia.dtos.user.UpdateUserRequest;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@PostMapping("/delete")
	public SuccessResponse delete(Principal principal) {
		userService.softDelete(principal.getName());
		return new SuccessResponse();
	}

	@PutMapping("/update")
	public SuccessResponse update(@RequestBody UpdateUserRequest request) {
		userService.update(request);
		return new SuccessResponse();
	}

	@GetMapping("/getall")
	public PageResponse<GetAllUserResponse> getAll() {
		return userService.getAll();

	}

	@PostMapping("/suspended")
	public SuccessResponse suspendedAccount(@RequestBody SuspendedUserRequest request) {
		userService.suspendedAccount(request);
		return new SuccessResponse();
	}

	@PutMapping("/changepassword")
	public SuccessResponse changePassword(@RequestBody ChangePasswordRequest request, Principal principal) {
		userService.changePassword(request, principal.getName());
		return new SuccessResponse();
	}

	@PostMapping("/follow")
	public SuccessResponse followUser(@RequestBody FollowUserRequest request) {
		userService.followUser(request);
		return new SuccessResponse();
	}

	@PostMapping("/unfollow")
	public SuccessResponse unfollowUser(@RequestBody UnfollowUserRequest request) {
		userService.unfollowUser(request);
		return new SuccessResponse();
	}

	@PostMapping("/removefollower")
	public SuccessResponse removeFollowerUser(@RequestBody RemoveFollowerUserRequest request) {
		userService.removeFollowerUser(request);
		return new SuccessResponse();
	}

	@PostMapping("/blockuser")
	public SuccessResponse blockUser(@RequestBody BlockUserRequest request) {
		userService.blockUser(request);
		return new SuccessResponse();
	}

}
