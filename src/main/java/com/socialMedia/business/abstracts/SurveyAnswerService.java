package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.surveyAnswer.CreateSurveyAnswerRequest;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.SurveyAnswer;
import com.socialMedia.entities.User;

public interface SurveyAnswerService {

	SurveyAnswer getSurveyAnswer(UUID id);

	List<SurveyAnswer> getSurveyAnswerWithSurveyId(UUID surveyId);

	SurveyAnswer create(CreateSurveyAnswerRequest request, Survey survey, User currentUser);

	void delete(UUID surveyId);
}
