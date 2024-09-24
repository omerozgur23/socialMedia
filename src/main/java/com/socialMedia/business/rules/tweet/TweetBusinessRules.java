package com.socialMedia.business.rules.tweet;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dataAccess.UserTweetRepository;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

@Service
public class TweetBusinessRules {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTweetRepository userTweetRepository;

	public User getCurrentUser(String currentUserEmail) {
		return userRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
	}

	public void validateUserTweetAssociation(UUID userId, UUID tweetId) {
		userTweetRepository.findByUserIdAndTweetId(userId, tweetId)
				.orElseThrow(() -> new BusinessException(Messages.USER_TWEET_NOT_FOUND));
	}
	
	public boolean checkIfUserRetweeted(User user, Tweet tweet) {
		boolean isRetweeted;
		if (tweet.getUserRetweets().contains(user))
			isRetweeted = true;
		else
			isRetweeted = false;
		return isRetweeted;
	}
}
