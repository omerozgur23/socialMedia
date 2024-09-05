package com.socialMedia.dtos.tweetVideo;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTweetVideosRequest {

	private UUID id;

	private String videoPath;
}
