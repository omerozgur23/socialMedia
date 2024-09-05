package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;
import com.socialMedia.entities.UserTweet;

public interface UserTweetService {

	void userTweetSoftDelete(UUID tweetId);

	void userTweetAdd(User user, Tweet tweet);

	List<UserTweet> getUserTweet(UUID userId);

	List<UUID> delete();
}
