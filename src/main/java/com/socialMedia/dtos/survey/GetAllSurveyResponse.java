package com.socialMedia.dtos.survey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.surveyAnswer.GetAllSurveyAnswerResponse;
import com.socialMedia.dtos.surveyOption.GetAllSurveyOptionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSurveyResponse {

	private UUID id;

	private String title;

	private String creatorUserName;

	private LocalDateTime createdDate;

	private LocalDateTime finishedDate;

	private boolean isFinished;

	private List<GetAllSurveyOptionResponse> surveyOptions;

	private List<GetAllSurveyAnswerResponse> surveyAnswers;
}
