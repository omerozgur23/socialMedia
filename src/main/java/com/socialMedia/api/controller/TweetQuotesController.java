package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.TweetQuoteService;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.tweet.CreateTweetQuoteRequest;

@RestController
@RequestMapping("/api/v1/tweetquotes")
public class TweetQuotesController {

	@Autowired
	private TweetQuoteService tweetQuoteService;

	@PostMapping("/create")
	public SuccessResponse tweetQuote(@RequestBody CreateTweetQuoteRequest request) {
		tweetQuoteService.tweetQuote(request);
		return new SuccessResponse();
	}
}
