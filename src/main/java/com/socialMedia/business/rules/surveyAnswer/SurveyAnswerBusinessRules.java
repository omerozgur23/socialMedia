package com.socialMedia.business.rules.surveyAnswer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.SurveyAnswerRepository;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.SurveyAnswer;

@Service
public class SurveyAnswerBusinessRules {

	@Autowired
	private SurveyAnswerRepository surveyAnswerRepository;

	public void validateUserHasNotVoted(UUID userId, UUID surveyId) {
		Optional<SurveyAnswer> surveyAnswer = surveyAnswerRepository.findByUserIdAndSurveyId(userId, surveyId);
		if (surveyAnswer.isPresent())
			throw new BusinessException(Messages.YOU_CAN_ONLY_VOTE_ONCE_IN_SURVEY);
	}

	public void validateCreatorCannotVote(Survey survey, UUID creatorId) {
		if (survey.getCreatorUser().getId().equals(creatorId))
			throw new BusinessException(Messages.SURVEY_CREATOR_CANNOT_VOTE_IN_THEIR_OWN_SURVEY);
	}

	public void checkIfSurveyIsFinished(Survey survey) {
		if (survey.isFinished())
			throw new BusinessException(Messages.SURVEY_IS_FINISHED_CANNOT_BE_ANSWER);
	}
}
