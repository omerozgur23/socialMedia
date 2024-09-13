package com.socialMedia.business.abstracts;

import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.survey.CreateSurveyRequest;
import com.socialMedia.dtos.survey.DeleteSurveyRequest;
import com.socialMedia.dtos.survey.GetAllSurveyResponse;
import com.socialMedia.dtos.surveyAnswer.CreateSurveyAnswerRequest;
import com.socialMedia.entities.Survey;

public interface SurveyService {

	PageResponse<GetAllSurveyResponse> getAll();

	Survey getSurvey(UUID id);

	Survey create(CreateSurveyRequest request);

	void surveyAnswer(CreateSurveyAnswerRequest request);

	void finalizeSurvey(Survey survey);

	void delete(DeleteSurveyRequest request);
}
