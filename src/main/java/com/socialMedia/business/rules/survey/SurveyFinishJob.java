package com.socialMedia.business.rules.survey;

import java.time.LocalDateTime;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialMedia.business.abstracts.SurveyService;
import com.socialMedia.entities.Survey;

@Component
public class SurveyFinishJob implements Job {

	@Autowired
	private SurveyService surveyService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String surveyIdString = (String) context.getJobDetail().getJobDataMap().get("surveyId");
		UUID surveyId = UUID.fromString(surveyIdString);

		Survey survey = surveyService.getSurvey(surveyId);
		if (survey.getFinishedDate().isBefore(LocalDateTime.now())) {
			survey.setFinished(true);
			surveyService.finalizeSurvey(survey);
		}
	}
}
