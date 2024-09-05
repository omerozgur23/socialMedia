package com.socialMedia.dtos.tweetVideo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTweetVideosRequest {

	private String videoPath;
}
