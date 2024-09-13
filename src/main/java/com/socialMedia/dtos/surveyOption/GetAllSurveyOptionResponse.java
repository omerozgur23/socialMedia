package com.socialMedia.dtos.surveyOption;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSurveyOptionResponse {

	private UUID id;

	private UUID surveyId;

	private String description;
}
