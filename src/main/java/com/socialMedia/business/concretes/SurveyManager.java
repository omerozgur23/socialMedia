package com.socialMedia.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialMedia.business.abstracts.SurveyService;
import com.socialMedia.business.abstracts.UserService;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.dtos.survey.CreateSurveyRequest;
import com.socialMedia.entities.Survey;

public class SurveyManager implements SurveyService {

	@Autowired
	private UserService userService;

	@Override
	public Survey create(CreateSurveyRequest request) {
		String currentUserEmail = AuthenticatedUser.getCurrentUser();

		return null;
	}

}
