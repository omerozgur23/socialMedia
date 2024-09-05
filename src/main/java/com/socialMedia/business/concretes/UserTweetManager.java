package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.UserTweetService;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.UserRepository;
import com.socialMedia.dataAccess.UserTweetRepository;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;
import com.socialMedia.entities.UserTweet;

@Service
public class UserTweetManager implements UserTweetService {

	@Autowired
	UserTweetRepository userTweetRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void userTweetSoftDelete(UUID tweetId) {
		UserTweet userTweet = userTweetRepository.findByTweetId(tweetId)
				.orElseThrow(() -> new BusinessException(Messages.USER_TWEET_NOT_FOUND));
		userTweet.setDeleted(true);
		userTweetRepository.save(userTweet);
	}

	@Override
	public void userTweetAdd(User user, Tweet tweet) {
		UserTweet userTweet = UserTweet.builder().user(user).tweet(tweet).build();
		userTweetRepository.save(userTweet);
	}

	@Override
	public List<UserTweet> getUserTweet(UUID userId) {
		return userTweetRepository.findByUserId(userId)
				.orElseThrow(() -> new BusinessException(Messages.USER_TWEET_NOT_FOUND));
	}

	@Override
	public List<UUID> delete() {
		String authenticatedUserEmail = AuthenticatedUser.getCurrentUser();
		System.out.println(authenticatedUserEmail);
		User currentUser = userRepository.findByEmail(authenticatedUserEmail).orElseThrow();
		List<UserTweet> userTweets = getUserTweet(currentUser.getId());
		List<UUID> tweetsId = userTweets.stream().map(ut -> ut.getTweet().getId()).toList();
		userTweetRepository.deleteAll(userTweets);
		return tweetsId;
	}

}
