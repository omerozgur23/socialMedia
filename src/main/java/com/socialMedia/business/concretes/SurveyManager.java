package com.socialMedia.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.SurveyAnswerService;
import com.socialMedia.business.abstracts.SurveyOptionService;
import com.socialMedia.business.abstracts.SurveyService;
import com.socialMedia.business.rules.survey.SurveyBusinessRules;
import com.socialMedia.business.rules.survey.SurveySchedulerService;
import com.socialMedia.core.utilities.AuthenticatedUser;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.SurveyRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.survey.CreateSurveyRequest;
import com.socialMedia.dtos.survey.DeleteSurveyRequest;
import com.socialMedia.dtos.survey.GetAllSurveyResponse;
import com.socialMedia.dtos.surveyAnswer.CreateSurveyAnswerRequest;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.User;

import jakarta.transaction.Transactional;

@Service
public class SurveyManager implements SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private SurveyOptionService surveyOptionService;

	@Autowired
	private SurveyAnswerService surveyAnswerService;

	@Autowired
	private SurveyBusinessRules surveyBusinessRules;

	@Autowired
	private ModelMapperService modelmapper;

	@Autowired
	private SurveySchedulerService surveySchedulerService;

	@Override
	public PageResponse<GetAllSurveyResponse> getAll() {
		List<Survey> surveys = surveyRepository.findAll();
		List<GetAllSurveyResponse> response = surveys.stream()
				.map(survey -> modelmapper.forResponse().map(survey, GetAllSurveyResponse.class)).toList();
		int count = response.size();
		return new PageResponse<GetAllSurveyResponse>(count, response);
	}

	@Override
	public Survey getSurvey(UUID id) {
		return surveyRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.SURVEY_NOT_FOUND + id));
	}

	@Transactional
	@Override
	public Survey create(CreateSurveyRequest request) {
		User creatorUser = AuthenticatedUser.getCurrentUser();
		LocalDateTime finishedDate = surveyBusinessRules.formatterDate(request.getFinishedDate());
		surveyBusinessRules.validateFinishedDateAfterNowPlusOneHour(finishedDate);
		surveyBusinessRules.validateFinishedDateAfterNowPlusEightDay(finishedDate);

		Survey survey = Survey.builder().createdDate(LocalDateTime.now()).creatorUser(creatorUser)
				.title(request.getTitle()).finishedDate(finishedDate).isFinished(false).build();
		surveyRepository.save(survey);

		surveyOptionService.create(survey, request.getSurveyOptions());

		surveySchedulerService.scheduleSurveyFinishJob(survey);
		return survey;
	}

	@Override
	public void surveyAnswer(CreateSurveyAnswerRequest request) {
		Survey survey = getSurvey(request.getSurvey());
		User currentUser = AuthenticatedUser.getCurrentUser();
		surveyAnswerService.create(request, survey, currentUser);
	}

	@Override
	public void finalizeSurvey(Survey survey) {
		Survey endedSurvey = getSurvey(survey.getId());
		endedSurvey.setFinished(true);
		surveyRepository.save(endedSurvey);
	}

	@Override
	public void delete(DeleteSurveyRequest request) {
		User currentUser = AuthenticatedUser.getCurrentUser();
		Survey survey = getSurvey(request.getId());
		surveyBusinessRules.validateUserIsSurveyOwner(survey, currentUser);
		surveyAnswerService.delete(survey.getId());
		surveyOptionService.delete(survey.getId());
		surveyRepository.delete(survey);
	}

}
