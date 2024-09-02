package com.socialMedia.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.TweetImagesService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.business.abstracts.TweetVideosService;
import com.socialMedia.business.rules.tweet.TweetBusinessRules;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.dataAccess.TweetRepository;
import com.socialMedia.dtos.tweet.CreateTweetImagesRequest;
import com.socialMedia.dtos.tweet.CreateTweetRequest;
import com.socialMedia.dtos.tweet.CreateTweetVideosRequest;
import com.socialMedia.entities.Tweet;

@Service
public class TweetManager implements TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Autowired
	private TweetBusinessRules tweetBusinessRules;

	@Autowired
	private TweetImagesService tweetImagesService;

	@Autowired
	private TweetVideosService tweetVideosService;

	@Override
	public void create(CreateTweetRequest tweetRequest, List<CreateTweetImagesRequest> tweetImagesRequest,
			List<CreateTweetVideosRequest> tweetVideosRequest) {
		Tweet tweet = modelMapper.forRequest().map(tweetRequest, Tweet.class);
//		BeanUtils.copyProperties(request, tweet, "id", "tweetImages", "tweetVideos", "createdDeleted", "deletedDate");
		tweetRepository.save(tweet);
		tweetImagesService.create(tweetImagesRequest);

//		if (!request.getTweetImages().isEmpty()) {
//			tweetImagesService.create(request.getTweetImages());
//		}
	}

}
