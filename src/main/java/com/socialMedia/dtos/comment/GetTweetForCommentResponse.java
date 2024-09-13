package com.socialMedia.dtos.comment;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTweetForCommentResponse {

	private UUID tweetId;

	private String tweetText;

	private String tweetImages;

	private String tweetVideos;

	private String voicePath;

	private String createdDate;
}
