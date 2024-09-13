package com.socialMedia.business.abstracts;

import java.util.List;
import java.util.UUID;

import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.surveyOption.CreateSurveyOptionRequest;
import com.socialMedia.dtos.surveyOption.GetAllSurveyOptionResponse;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.SurveyOption;

public interface SurveyOptionService {

	SurveyOption getSurveyOption(UUID id);

	List<SurveyOption> getSurveyOptionsWithSurveyId(UUID surveyId);

	PageResponse<GetAllSurveyOptionResponse> getAll();

	List<SurveyOption> create(Survey survey, List<CreateSurveyOptionRequest> request);

	void delete(UUID surveyId);
}
