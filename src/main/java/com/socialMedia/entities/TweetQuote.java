package com.socialMedia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweet_quotes")
public class TweetQuote {
	
	@Id
	@GeneratedValue
	private UUID id;
	
//	private String text;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "tweet_id")
	private Tweet tweet;
	
	@ManyToOne
	@JoinColumn(name = "tweet_quote_id")
	private Tweet tweetQuote;
	
//	@OneToMany(mappedBy = "tweetQuote")
//	private List<TweetQuoteImage> tweetQuoteImages;
//
//	@OneToMany(mappedBy = "tweetQuote")
//	private List<TweetQuoteVideo> tweetQuoteVideos;
//	
//	private LocalDateTime createdDate;
//	
//	private LocalDateTime deletedDate;

}
