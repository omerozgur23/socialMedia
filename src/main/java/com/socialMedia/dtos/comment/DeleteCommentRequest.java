package com.socialMedia.dtos.comment;

import java.util.UUID;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCommentRequest {

	@NotNull(message = Messages.COMMENT_ID_CANNOR_BE_NULL)
	private UUID id;
}
