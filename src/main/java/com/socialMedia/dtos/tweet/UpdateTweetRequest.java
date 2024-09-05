package com.socialMedia.dtos.tweet;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTweetRequest {

	private UUID id;

	private String text;

	private String voicePath;
}
