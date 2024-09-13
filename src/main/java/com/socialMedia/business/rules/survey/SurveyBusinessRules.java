package com.socialMedia.business.rules.survey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.User;

@Service
public class SurveyBusinessRules {

	public LocalDateTime formatterDate(String request) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(request, formatter);
		return dateTime;
	}

	public void validateFinishedDateAfterNowPlusOneHour(LocalDateTime finishedDate) {
		LocalDateTime afterFiveMinute = LocalDateTime.now().plusMinutes(5);
		if (finishedDate.isBefore(afterFiveMinute))
			throw new BusinessException(Messages.SURVEY_FINISHED_DATE_MUST_BE_AT_LEAST_5_MINUTES_LATER);
	}

	public void validateFinishedDateAfterNowPlusEightDay(LocalDateTime finishedDate) {
		LocalDateTime afterEightDay = LocalDateTime.now().plusDays(8);
		if (finishedDate.isAfter(afterEightDay))
			throw new BusinessException(Messages.SURVEY_FINISHED_DATE_MUST_BE_WITHIN_EIGHT_DAYS);
	}

	public void validateUserIsSurveyOwner(Survey survey, User user) {
		if (!survey.getCreatorUser().getId().equals(user.getId()))
			throw new BusinessException(Messages.CANNOT_DELETE_SOMEONE_ELSE_SURVEY);
	}
}
