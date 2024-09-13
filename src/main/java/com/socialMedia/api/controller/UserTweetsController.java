package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.UserTweetService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.tweet.GetTweetsByUserIdResponse;

@RestController
@RequestMapping("/api/v1/usertweets")
public class UserTweetsController {

	@Autowired
	private UserTweetService userTweetService;

	@GetMapping("/get")
	public PageResponse<GetTweetsByUserIdResponse> getTweetsByUserId() {
		return userTweetService.getTweetsByUserId();
	}
}
