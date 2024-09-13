package com.socialMedia.dtos.tweet;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentForTweetResponse {

	private UUID id;

	private String createdDate;

	private String comment;

//	private UUID userId;

	private String userName;

//	private List<GetTweetLikesResponse> commentLikes;
}
