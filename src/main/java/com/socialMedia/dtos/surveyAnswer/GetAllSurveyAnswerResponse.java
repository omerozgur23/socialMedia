package com.socialMedia.dtos.surveyAnswer;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSurveyAnswerResponse {

	private UUID id;

	private String userName;

	private String surveyOptionDescription;
}
