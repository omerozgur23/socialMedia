package com.socialMedia.dtos.tweet;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.tweetImage.GetAllTweetImageResponse;
import com.socialMedia.dtos.tweetVideo.GetAllTweetVideoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTweetResponse {

	private UUID id;

	private String text;

	private String voicePath;

	private List<GetAllTweetImageResponse> tweetImages;

	private List<GetAllTweetVideoResponse> tweetVideos;
}
