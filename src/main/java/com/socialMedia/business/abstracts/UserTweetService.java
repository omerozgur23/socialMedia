package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.tweet.GetTweetsByUserIdResponse;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;
import com.socialMedia.entities.UserTweet;

public interface UserTweetService {

	PageResponse<GetTweetsByUserIdResponse> getTweetsByUserId();

	void userTweetSoftDelete(UUID tweetId);

	UserTweet userTweetAdd(User user, Tweet tweet);

	List<UserTweet> getUserTweets(List<UUID> usersId);

	List<UUID> delete(List<UUID> usersId);
}
