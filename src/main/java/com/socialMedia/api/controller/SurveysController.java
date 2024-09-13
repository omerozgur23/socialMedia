package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SurveyService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.SuccessResponse;
import com.socialMedia.dtos.survey.CreateSurveyRequest;
import com.socialMedia.dtos.survey.DeleteSurveyRequest;
import com.socialMedia.dtos.survey.GetAllSurveyResponse;
import com.socialMedia.dtos.surveyAnswer.CreateSurveyAnswerRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/surveys")
public class SurveysController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping("/getall")
	public PageResponse<GetAllSurveyResponse> getAll() {
		return surveyService.getAll();
	}

	@PostMapping("/create")
	public SuccessResponse create(@Valid @RequestBody CreateSurveyRequest request) {
		surveyService.create(request);
		return new SuccessResponse();
	}

	@PostMapping("/answer")
	public SuccessResponse surveyAnswer(@Valid @RequestBody CreateSurveyAnswerRequest request) {
		surveyService.surveyAnswer(request);
		return new SuccessResponse();
	}

	@PostMapping("/delete")
	public SuccessResponse delete(@RequestBody DeleteSurveyRequest request) {
		surveyService.delete(request);
		return new SuccessResponse();
	}
}
