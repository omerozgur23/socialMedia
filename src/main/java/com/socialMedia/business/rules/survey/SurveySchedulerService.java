package com.socialMedia.business.rules.survey;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.entities.Survey;

@Service
public class SurveySchedulerService {

	@Autowired
	private Scheduler scheduler;

	public void scheduleSurveyFinishJob(Survey survey) {
		// Job detaylarını oluştur (SurveyFinishJob çalışacak)
		JobDetail jobDetail = JobBuilder.newJob(SurveyFinishJob.class).withIdentity(survey.getId().toString())
				.usingJobData("surveyId", survey.getId().toString()).build();

		// Job'un tetikleneceği zamanı belirle (anketin bitiş zamanı)
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + survey.getId().toString())
				.startAt(java.sql.Timestamp.valueOf(survey.getFinishedDate())).build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
