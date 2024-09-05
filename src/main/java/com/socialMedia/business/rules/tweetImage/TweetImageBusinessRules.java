package com.socialMedia.business.rules.tweetImage;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetImageRepository;
import com.socialMedia.entities.TweetImage;

@Service
public class TweetImageBusinessRules {

	@Autowired
	private TweetImageRepository tweetImageRepository;

	public List<TweetImage> checkTweetImagesWithTweetId(List<UUID> tweetsId) {
		return tweetImageRepository.findByTweetIdIn(tweetsId)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_IMAGE_NOT_FOUND_WITH_TWEET_ID + tweetsId));
	}
}
