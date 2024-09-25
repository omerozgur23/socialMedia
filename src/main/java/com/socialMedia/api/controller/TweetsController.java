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
import com.socialMedia.dtos.tweet.CreateRetweetRequest;
import com.socialMedia.dtos.tweet.CreateTweetDTO;
import com.socialMedia.dtos.tweet.DeleteRetweetRequest;
import com.socialMedia.dtos.tweet.DeleteTweetRequest;
import com.socialMedia.dtos.tweet.GetAllTweetResponse;
import com.socialMedia.dtos.tweet.UpdateTweetDTO;

import jakarta.validation.Valid;

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
	public SuccessResponse create(@Valid @RequestBody CreateTweetDTO request) {
		tweetService.create(request);
		return new SuccessResponse();
	}

	@PutMapping("/update")
	public SuccessResponse update(@Valid @RequestBody UpdateTweetDTO request, Principal principal) {
		tweetService.update(request, principal.getName());
		return new SuccessResponse();
	}

	@PostMapping("/retweet")
	public SuccessResponse retweet(@RequestBody CreateRetweetRequest request) {
		tweetService.retweet(request);
		return new SuccessResponse();
	}

	@PostMapping("/undoretweet")
	public SuccessResponse undoRetweet(@RequestBody DeleteRetweetRequest request) {
		tweetService.undoRetweet(request);
		return new SuccessResponse();
	}

	@PostMapping("/delete")
	public SuccessResponse delete(@RequestBody DeleteTweetRequest request) {
		tweetService.softDelete(request);
		return new SuccessResponse();
	}

}
