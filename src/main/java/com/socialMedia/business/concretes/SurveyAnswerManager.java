package com.socialMedia.business.concretes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.SurveyAnswerService;
import com.socialMedia.business.abstracts.SurveyOptionService;
import com.socialMedia.business.rules.surveyAnswer.SurveyAnswerBusinessRules;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.SurveyAnswerRepository;
import com.socialMedia.dtos.surveyAnswer.CreateSurveyAnswerRequest;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.SurveyAnswer;
import com.socialMedia.entities.SurveyOption;
import com.socialMedia.entities.User;

@Service
public class SurveyAnswerManager implements SurveyAnswerService {

	@Autowired
	private SurveyAnswerRepository surveyAnswerRepository;

	@Autowired
	private SurveyOptionService surveyOptionService;

	@Autowired
	private SurveyAnswerBusinessRules surveyAnswerBusinessRules;

	@Override
	public SurveyAnswer getSurveyAnswer(UUID id) {
		return surveyAnswerRepository.findById(id)
				.orElseThrow(() -> new BusinessException(Messages.SURVEY_ANSWER_NOT_FOUND + id));
	}

	@Override
	public List<SurveyAnswer> getSurveyAnswerWithSurveyId(UUID surveyId) {
		return surveyAnswerRepository.findBySurveyId(surveyId)
				.orElseThrow(() -> new BusinessException(Messages.SURVEY_ANSWER_NOT_FOUND + surveyId));
	}

	@Override
	public SurveyAnswer create(CreateSurveyAnswerRequest request, Survey survey, User currentUser) {
		surveyAnswerBusinessRules.checkIfSurveyIsFinished(survey);
		surveyAnswerBusinessRules.validateCreatorCannotVote(survey, currentUser.getId());
		surveyAnswerBusinessRules.validateUserHasNotVoted(currentUser.getId(), survey.getId());

		SurveyOption surveyOption = surveyOptionService.getSurveyOption(request.getSurveyOption());
		SurveyAnswer surveyAnswer = SurveyAnswer.builder().user(currentUser).survey(survey).surveyOption(surveyOption)
				.build();
		surveyAnswerRepository.save(surveyAnswer);
		return surveyAnswer;
	}

	@Override
	public void delete(UUID surveyId) {
		List<SurveyAnswer> surveyAnswers = getSurveyAnswerWithSurveyId(surveyId);
		surveyAnswerRepository.deleteAll(surveyAnswers);
	}

}
