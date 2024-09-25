package com.socialMedia.dtos.tweet;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTweetQuoteRequest {

	private CreateTweetDTO tweetRequest;

	private UUID tweetQuoteId;
}
