package com.socialMedia.dtos.tweetVideo;

import java.util.UUID;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTweetVideosRequest {

	private UUID id;

	@Size(max = 200, message = Messages.YOU_HAVE_EXISTIS_EXCEEDED_THE_CHARACTER_LIMIT_200)
	private String videoPath;
}
