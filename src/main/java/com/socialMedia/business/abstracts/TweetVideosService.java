package com.socialMedia.business.abstracts;

import java.util.List;

import com.socialMedia.dtos.tweet.CreateTweetVideosRequest;

public interface TweetVideosService {

	void create(List<CreateTweetVideosRequest> request);

}
