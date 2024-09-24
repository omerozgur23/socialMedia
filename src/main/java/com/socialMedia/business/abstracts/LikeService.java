package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.like.CreateCommentLikeRequest;
import com.socialMedia.dtos.like.CreateTweetLikeRequest;
import com.socialMedia.entities.Comment;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

public interface LikeService {

	void tweetLike(CreateTweetLikeRequest request);
	
	void tweetUnlike(User user, Tweet tweet);

	void commentLike(CreateCommentLikeRequest request);
	
	void commentUnlike(User user, Comment comment);
}
