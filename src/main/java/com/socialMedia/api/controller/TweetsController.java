package com.socialMedia.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.tweet.CreateTweetDTO;
import com.socialMedia.dtos.tweet.DeleteTweetRequest;
import com.socialMedia.dtos.tweet.GetAllTweetResponse;
import com.socialMedia.dtos.tweet.UpdateTweetDTO;

@RestController
@RequestMapping("/api/v1/tweets")
public class TweetsController {

	@Autowired
	private TweetService tweetService;

	@GetMapping("/getall")
	public PageResponse<GetAllTweetResponse> getAll() {
		return tweetService.getAll();
	}

	@PostMapping("/create")
	public SuccessResponse create(@RequestBody CreateTweetDTO request, Principal principal) {
		tweetService.create(request, principal.getName());
		return new SuccessResponse();
	}

	@PutMapping("/update")
	public SuccessResponse update(@RequestBody UpdateTweetDTO request, Principal principal) {
		tweetService.update(request, principal.getName());
		return new SuccessResponse();
	}

	@PostMapping("/delete")
	public SuccessResponse delete(@RequestBody DeleteTweetRequest request) {
		tweetService.softDelete(request);
		return new SuccessResponse();
	}

}
