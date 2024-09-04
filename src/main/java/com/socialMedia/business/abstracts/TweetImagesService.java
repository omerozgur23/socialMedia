package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.tweetImage.CreateTweetImagesRequest;
import com.socialMedia.dtos.tweetImage.UpdateTweetImagesRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetImage;

public interface TweetImagesService {

	void create(Tweet tweet, List<CreateTweetImagesRequest> request);

	void update(List<UpdateTweetImagesRequest> request);

	void delete(List<UUID> tweetId);

	TweetImage updateSingleTweetImage(UpdateTweetImagesRequest request);

	TweetImage getTweetImages(UUID id);
}
