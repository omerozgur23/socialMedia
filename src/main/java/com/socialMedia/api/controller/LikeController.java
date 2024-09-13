package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.LikeService;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.like.CreateCommentLikeRequest;
import com.socialMedia.dtos.like.CreateTweetLikeRequest;

@RestController
@RequestMapping("/api/v1")
public class LikeController {

	@Autowired
	private LikeService likeService;

	@PostMapping("/tweetlike")
	public SuccessResponse tweetLike(@RequestBody CreateTweetLikeRequest request) {
		likeService.tweetLike(request);
		return new SuccessResponse();
	}

	@PostMapping("/commentlike")
	public SuccessResponse commentLike(@RequestBody CreateCommentLikeRequest request) {
		likeService.commentLike(request);
		return new SuccessResponse();
	}
}
