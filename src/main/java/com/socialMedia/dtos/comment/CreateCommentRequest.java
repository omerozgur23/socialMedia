package com.socialMedia.dtos.comment;

import java.util.UUID;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {

	@NotBlank(message = Messages.COMMENT_CANNOT_BE_BLANK)
	@Size(min = 1, max = 280, message = Messages.YOU_HAVE_EXISTIS_EXCEEDED_THE_COMMENT_CHARACTER_LIMIT_280)
	private String comment;

	@NotNull(message = Messages.TWEET_ID_CANNOT_BE_NULL_IN_COMMENT)
	private UUID tweetId;
}
