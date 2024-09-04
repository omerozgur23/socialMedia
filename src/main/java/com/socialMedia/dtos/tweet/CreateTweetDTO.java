package com.socialMedia.dtos.tweet;

import java.util.Collections;
import java.util.List;

import com.socialMedia.dtos.tweetImage.CreateTweetImagesRequest;
import com.socialMedia.dtos.tweetVideo.CreateTweetVideosRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTweetDTO {

	private CreateTweetRequest tweetRequest;

	@Singular("imageRequest")
	private final List<CreateTweetImagesRequest> imagesRequest = Collections.emptyList();

	@Singular("videoRequest")
	private final List<CreateTweetVideosRequest> videosRequest = Collections.emptyList();
}
