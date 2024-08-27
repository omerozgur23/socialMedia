package com.socialMedia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweet_videos")
public class TweetVideo {

	@Id
	@GeneratedValue
	private UUID id;

	private String videoPath;

	@ManyToOne
	private Tweet tweet;
}
