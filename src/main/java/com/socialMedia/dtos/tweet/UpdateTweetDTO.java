package com.socialMedia.dtos.tweet;

import java.util.Collections;
import java.util.List;

import com.socialMedia.dtos.tweetImage.UpdateTweetImagesRequest;
import com.socialMedia.dtos.tweetVideo.UpdateTweetVideosRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTweetDTO {

	private UpdateTweetRequest tweetRequest;

	@Singular
	private final List<UpdateTweetImagesRequest> tweetImages = Collections.emptyList();

	@Singular
	private final List<UpdateTweetVideosRequest> tweetVideos = Collections.emptyList();

}
