package com.socialMedia.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialMedia.business.abstracts.TweetImagesService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.dataAccess.TweetImageRepository;
import com.socialMedia.dtos.tweet.CreateTweetImagesRequest;
import com.socialMedia.entities.TweetImage;

public class TweetImagesmManager implements TweetImagesService {

	@Autowired
	private TweetImageRepository tweetImageRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public void create(List<CreateTweetImagesRequest> request) {
//		List<TweetImage> tweetImages = new ArrayList<>();
		List<TweetImage> createImages = request.stream()
				.map(tweetImage -> modelMapper.forRequest().map(tweetImage, TweetImage.class)).toList();
//		for (CreateTweetImagesRequest tweetImage : request) {
//			tweetImages = modelMapper.forRequest().map(tweetImage, TweetImage.class);
//		}
		tweetImageRepository.saveAll(createImages);
		System.out.println("Tweet Images Created");
	}

}
