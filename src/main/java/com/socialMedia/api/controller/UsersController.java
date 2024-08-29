package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.dtos.SuccessResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

	@Autowired
	private UserService userService;

	@PostMapping("/delete")
	public SuccessResponse delete() {
		userService.softDelete();
		return new SuccessResponse();
	}

}
