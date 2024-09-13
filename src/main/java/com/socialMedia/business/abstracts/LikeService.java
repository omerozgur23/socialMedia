package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.like.CreateCommentLikeRequest;
import com.socialMedia.dtos.like.CreateTweetLikeRequest;

public interface LikeService {

	void tweetLike(CreateTweetLikeRequest request);

	void commentLike(CreateCommentLikeRequest request);
}
