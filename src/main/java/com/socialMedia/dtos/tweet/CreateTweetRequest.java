package com.socialMedia.dtos.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTweetRequest {

	private String text;

	private String voicePath;

}
