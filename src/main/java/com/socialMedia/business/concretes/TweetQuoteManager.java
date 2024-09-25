package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.TweetQuoteService;
import com.socialMedia.business.abstracts.TweetService;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.TweetQuoteRepository;
import com.socialMedia.dtos.tweet.CreateTweetQuoteRequest;
import com.socialMedia.entities.Tweet;
import com.socialMedia.entities.TweetQuote;
import com.socialMedia.entities.User;

import jakarta.transaction.Transactional;

@Service
public class TweetQuoteManager implements TweetQuoteService {

	@Autowired
	private TweetService tweetService;

	@Autowired
	private TweetQuoteRepository tweetQuoteRepository;

	@Transactional
	@Override
	public TweetQuote tweetQuote(CreateTweetQuoteRequest request) {
		Tweet tweet = tweetService.create(request.getTweetRequest());
		User user = AuthenticatedUser.getCurrentUser();
		Tweet quote = tweetService.getTweet(request.getTweetQuoteId());
		TweetQuote tweetQuote = TweetQuote.builder().user(user).tweet(tweet).tweetQuote(quote).build();

		tweetQuoteRepository.save(tweetQuote);
		return tweetQuote;
	}

	@Override
	public List<TweetQuote> getUserQuotes(List<UUID> usersId) {
		return tweetQuoteRepository.findByUserIdIn(usersId)
				.orElseThrow(() -> new BusinessException(Messages.USER_TWEET_NOT_FOUND));
	}

	@Override
	public void deleteQuote(List<UUID> usersId) {
		List<TweetQuote> tweetQuotes = getUserQuotes(usersId);
		tweetQuoteRepository.deleteAll(tweetQuotes);
	}

}
