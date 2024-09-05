package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.TweetVideosService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetVideoRepository;
import com.socialMedia.dtos.tweetVideo.CreateTweetVideosRequest;
import com.socialMedia.dtos.tweetVideo.UpdateTweetVideosRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetVideo;

@Service
public class TweetVideosManager implements TweetVideosService {

	@Autowired
	private TweetVideoRepository tweetVideoRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public void create(Tweet tweet, List<CreateTweetVideosRequest> request) {
		List<TweetVideo> tweetVideos = request.stream().map(t -> {
			TweetVideo tweetVideo = modelMapper.forRequest().map(t, TweetVideo.class);
			tweetVideo.setTweet(tweet);
			return tweetVideo;
		}).toList();

		tweetVideoRepository.saveAll(tweetVideos);
	}

	@Override
	public void update(List<UpdateTweetVideosRequest> request) {
		List<TweetVideo> tweetVideos = request.stream().map(this::updateSingleTweetVideo).toList();
		tweetVideoRepository.saveAll(tweetVideos);
	}

	@Override
	public TweetVideo updateSingleTweetVideo(UpdateTweetVideosRequest request) {
		TweetVideo tweetVideo = getTweetVideo(request.getId());
		BeanUtils.copyProperties(request, tweetVideo, "id");
		return tweetVideo;
	}

	@Override
	public TweetVideo getTweetVideo(UUID id) {
		return tweetVideoRepository.findById(id)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_VIDEO_ID_NOT_FOUND + id));
	}

	@Override
	public void delete(List<UUID> tweetId) {
		List<TweetVideo> tweetVideos = tweetVideoRepository.findByTweetIdIn(tweetId)
				.orElseThrow(() -> new BusinessException(Messages.TWEET_VIDEO_NOT_FOUND_WITH_TWEETID + tweetId));
		tweetVideoRepository.deleteAll(tweetVideos);
	}

}
