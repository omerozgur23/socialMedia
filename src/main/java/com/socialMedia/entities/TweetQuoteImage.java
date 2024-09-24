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
@Table(name = "tweet_quote_images")
public class TweetQuoteImage {
	
	@Id
	@GeneratedValue
	private UUID id;

	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "tweet_quote_id")
	private TweetQuote tweetQuote;

}
