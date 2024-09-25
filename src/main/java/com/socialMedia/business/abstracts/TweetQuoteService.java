package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.tweet.CreateTweetQuoteRequest;
import com.socialMedia.entities.TweetQuote;

public interface TweetQuoteService {

	TweetQuote tweetQuote(CreateTweetQuoteRequest request);

	List<TweetQuote> getUserQuotes(List<UUID> usersId);

	void deleteQuote(List<UUID> usersId);
}
