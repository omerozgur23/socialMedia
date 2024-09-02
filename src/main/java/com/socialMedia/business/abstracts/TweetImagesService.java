package com.socialMedia.business.abstracts;

import java.util.List;

import com.socialMedia.dtos.tweet.CreateTweetImagesRequest;

public interface TweetImagesService {

	void create(List<CreateTweetImagesRequest> request);
}
