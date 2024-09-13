package com.socialMedia.business.rules.like;

import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

@Service
public class TweetLikeBusinessRules {

	public void validateIfUserLikeTweet(User user, Tweet tweet) {
		if (tweet.getUserLikes().contains(user))
			throw new BusinessException(Messages.TWEET_IS_ALREADY_LIKED);
	}
}
