package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.tweetVideo.CreateTweetVideosRequest;
import com.socialMedia.dtos.tweetVideo.UpdateTweetVideosRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetVideo;

public interface TweetVideosService {

	void create(Tweet tweet, List<CreateTweetVideosRequest> request);

	void update(List<UpdateTweetVideosRequest> request);

	void delete(List<UUID> tweetsId);

	TweetVideo updateSingleTweetVideo(UpdateTweetVideosRequest request);

	TweetVideo getTweetVideo(UUID id);
}
