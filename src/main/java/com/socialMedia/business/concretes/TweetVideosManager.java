package com.socialMedia.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialMedia.business.abstracts.TweetVideosService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.dataAccess.TweetVideoRepository;
import com.socialMedia.dtos.tweet.CreateTweetVideosRequest;

public class TweetVideosManager implements TweetVideosService {

	@Autowired
	private TweetVideoRepository tweetVideoRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public void create(List<CreateTweetVideosRequest> request) {

	}

}
