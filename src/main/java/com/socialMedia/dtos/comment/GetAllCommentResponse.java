package com.socialMedia.dtos.comment;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentResponse {

	private GetTweetForCommentResponse tweet;

	private UUID id;

	private String createdDate;

	private String comment;

	private UUID userId;

	private String userName;
}
