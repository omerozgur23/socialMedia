package com.socialMedia.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.TweetImagesService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.business.abstracts.TweetVideosService;
import com.socialMedia.business.abstracts.UserTweetService;
import com.socialMedia.business.rules.tweet.TweetBusinessRules;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.tweet.CreateTweetDTO;
import com.socialMedia.dtos.tweet.DeleteTweetRequest;
import com.socialMedia.dtos.tweet.GetAllTweetResponse;
import com.socialMedia.dtos.tweet.UpdateTweetDTO;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.User;

import jakarta.transaction.Transactional;

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

	@Autowired
	private UserTweetService userTweetService;

	@Override
	public PageResponse<GetAllTweetResponse> getAll() {
		List<Tweet> tweets = tweetRepository.findAll();
		List<GetAllTweetResponse> response = tweets.stream()
				.map(t -> modelMapper.forResponse().map(t, GetAllTweetResponse.class)).toList();
		int count = tweets.size();
		return new PageResponse<GetAllTweetResponse>(count, response);
	}

	@Transactional
	@Override
	public Tweet create(CreateTweetDTO request, String currentUserEmail) {
		User user = tweetBusinessRules.getCurrentUser(currentUserEmail);
		Tweet tweet = modelMapper.forRequest().map(request.getTweetRequest(), Tweet.class);
		tweet.setCreatedDate(LocalDateTime.now());
		tweetRepository.save(tweet);

		if (request.getImagesRequest() != null && !request.getImagesRequest().isEmpty())
			tweetImagesService.create(tweet, request.getImagesRequest());

		if (request.getVideosRequest() != null && !request.getVideosRequest().isEmpty())
			tweetVideosService.create(tweet, request.getVideosRequest());

		userTweetService.userTweetAdd(user, tweet);
		return tweet;
	}

	@Transactional
	@Override
	public Tweet update(UpdateTweetDTO request, String currentUserEmail) {
		User user = tweetBusinessRules.getCurrentUser(currentUserEmail);
		Tweet tweet = getTweet(request.getTweetRequest().getId());

		tweetBusinessRules.validateUserTweetAssociation(user.getId(), tweet.getId());

		BeanUtils.copyProperties(request.getTweetRequest(), tweet, "createdDate", "deletedDate");
		tweetRepository.save(tweet);

		if (request.getTweetImages() != null && !request.getTweetImages().isEmpty())
			tweetImagesService.update(request.getTweetImages());

		if (request.getTweetVideos() != null && !request.getTweetVideos().isEmpty())
			tweetVideosService.update(request.getTweetVideos());

		return tweet;
	}

	@Override
	public void softDelete(DeleteTweetRequest request) {
		Tweet tweet = getTweet(request.getId());
		tweet.setDeletedDate(LocalDateTime.now());

		tweetRepository.save(tweet);
		userTweetService.userTweetSoftDelete(tweet.getId());
	}

	@Override
	public Tweet getTweet(UUID id) {
		return tweetRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.TWEET_NOT_FOUND));
	}

	@Override
	public void hardDelete(List<UUID> tweetsId) {
		List<Tweet> tweets = tweetsId.stream().map(
				id -> tweetRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.TWEET_NOT_FOUND)))
				.toList();
		tweetRepository.deleteAll(tweets);
	}
}
