package com.socialMedia.business.rules.like;

import org.springframework.stereotype.Service;

import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

@Service
public class TweetLikeBusinessRules {

	public boolean validateIfUserLikeTweet(User user, Tweet tweet) {
		if (tweet.getUserLikes().contains(user))
			return true;
		return false;
	}
}
