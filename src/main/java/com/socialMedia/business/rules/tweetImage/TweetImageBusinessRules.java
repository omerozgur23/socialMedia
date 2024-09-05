package com.socialMedia.business.rules.tweetImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.dataAccess.TweetImageRepository;

@Service
public class TweetImageBusinessRules {

	@Autowired
	private TweetImageRepository tweetImageRepository;

}
