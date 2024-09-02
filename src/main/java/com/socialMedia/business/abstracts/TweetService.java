package com.socialMedia.business.abstracts;

import java.util.List;

import com.socialMedia.dtos.tweet.CreateTweetImagesRequest;
import com.socialMedia.dtos.tweet.CreateTweetRequest;
import com.socialMedia.dtos.tweet.CreateTweetVideosRequest;

public interface TweetService {

	void create(CreateTweetRequest tweetRequest, List<CreateTweetImagesRequest> tweetImagesRequest,
			List<CreateTweetVideosRequest> tweetVideosRequest);
}
