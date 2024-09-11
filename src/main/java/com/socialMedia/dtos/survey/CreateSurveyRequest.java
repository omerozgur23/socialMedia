package com.socialMedia.dtos.survey;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.surveyOption.CreateSurveyOptionRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyRequest {

	private UUID creatorUser;

	private String finishedDate;

	private List<CreateSurveyOptionRequest> surveyOptions;

}
