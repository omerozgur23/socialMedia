package com.socialMedia.business.rules.tweetVideo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.dataAccess.TweetVideoRepository;

@Service
public class TweetVideoBusinessRules {

	@Autowired
	private TweetVideoRepository tweetVideoRepository;

}
