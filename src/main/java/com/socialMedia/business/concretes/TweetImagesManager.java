package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.TweetImagesService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetImageRepository;
import com.socialMedia.dtos.tweetImage.CreateTweetImagesRequest;
import com.socialMedia.dtos.tweetImage.UpdateTweetImagesRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetImage;

@Service
public class TweetImagesManager implements TweetImagesService {

	@Autowired
	private TweetImageRepository tweetImageRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public void create(Tweet tweet, List<CreateTweetImagesRequest> request) {
		List<TweetImage> tweetImages = request.stream().map(t -> {
			TweetImage tweetImage = modelMapper.forRequest().map(t, TweetImage.class);
			tweetImage.setTweet(tweet);
			return tweetImage;
		}).toList();
		tweetImageRepository.saveAll(tweetImages);
	}

	@Override
	public void update(List<UpdateTweetImagesRequest> request) {
		List<TweetImage> tweetImages = request.stream().map(this::updateSingleTweetImage).toList();
		tweetImageRepository.saveAll(tweetImages);
	}

	@Override
	public TweetImage updateSingleTweetImage(UpdateTweetImagesRequest request) {
		TweetImage tweetImage = getTweetImages(request.getId());
		BeanUtils.copyProperties(request, tweetImage, "id");
		return tweetImage;
	}

	@Override
	public TweetImage getTweetImages(UUID id) {
		return tweetImageRepository.findById(id)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_IMAGE_ID_NOT_FOUND + id));
	}

	@Override
	public void delete(List<UUID> tweetId) {
		List<TweetImage> tweetImages = tweetImageRepository.findByTweetIdIn(tweetId)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_VIDEO_NOT_FOUND_WITH_TWEETID + tweetId));
		tweetImageRepository.deleteAll(tweetImages);
	}
}
