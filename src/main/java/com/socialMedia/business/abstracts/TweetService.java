package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.tweet.CreateRetweetRequest;
import com.socialMedia.dtos.tweet.CreateTweetDTO;
import com.socialMedia.dtos.tweet.DeleteRetweetRequest;
import com.socialMedia.dtos.tweet.DeleteTweetRequest;
import com.socialMedia.dtos.tweet.GetAllTweetResponse;
import com.socialMedia.dtos.tweet.UpdateTweetDTO;
import com.socialMedia.entities.Tweet;

public interface TweetService {

	PageResponse<GetAllTweetResponse> getAll();

	Tweet getTweet(UUID id);

	Tweet create(CreateTweetDTO request);

	Tweet update(UpdateTweetDTO request, String currentUser);

	Tweet retweet(CreateRetweetRequest request);

	Tweet undoRetweet(DeleteRetweetRequest request);

	void softDelete(DeleteTweetRequest request);

	void hardDelete(List<UUID> tweetsId);

}