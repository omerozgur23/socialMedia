package com.socialMedia.business.abstracts;

import com.socialMedia.dtos.survey.CreateSurveyRequest;
import com.socialMedia.entities.Survey;

public interface SurveyService {

	Survey create(CreateSurveyRequest request);

}
