package com.socialMedia.business.rules.tweetVideo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetVideoRepository;
import com.socialMedia.entities.TweetVideo;

@Service
public class TweetVideoBusinessRules {

	@Autowired
	private TweetVideoRepository tweetVideoRepository;

	public List<TweetVideo> checkTweetVideosWithTweetId(List<UUID> tweetsId) {
		return tweetVideoRepository.findByTweetIdIn(tweetsId)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_VIDEO_NOT_FOUND_WITH_TWEET_ID + tweetsId));
	}
}
