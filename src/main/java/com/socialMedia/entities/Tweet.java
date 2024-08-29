package com.socialMedia.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweets")
public class Tweet {

	@Id
	@GeneratedValue
	private UUID id;

	private String text;

	@OneToMany(mappedBy = "tweet")
	private List<TweetImage> tweetImages;

	@OneToMany(mappedBy = "tweet")
	private List<TweetVideo> tweetVideos;

	private String voicePath;

	private String createdDate;

	private String deletedDate;

	@OneToOne(mappedBy = "tweet")
	private UserTweet userTweet;

	@ManyToMany
	@JoinTable(name = "likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
	private List<User> userLikes;

	@ManyToMany
	@JoinTable(name = "retweets", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
	private List<User> userRetweets;

	@OneToMany(mappedBy = "tweet")
	private List<Comment> tweetComment;

}
