package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.tweet.CreateTweetDTO;
import com.socialMedia.dtos.tweet.DeleteTweetRequest;
import com.socialMedia.dtos.tweet.GetAllTweetResponse;
import com.socialMedia.dtos.tweet.UpdateTweetDTO;
import com.socialMedia.entities.Tweet;

public interface TweetService {

	PageResponse<GetAllTweetResponse> getAll();

	Tweet create(CreateTweetDTO request, String currentUser);

	Tweet update(UpdateTweetDTO request, String currentUser);

	void softDelete(DeleteTweetRequest request);

	void hardDelete(List<UUID> tweetsId);

	Tweet getTweet(UUID id);

}
