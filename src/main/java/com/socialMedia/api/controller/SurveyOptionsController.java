package com.socialMedia.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialMedia.business.abstracts.SurveyOptionService;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.surveyOption.GetAllSurveyOptionResponse;

@RestController
@RequestMapping("/api/v1/surveyoptions")
public class SurveyOptionsController {

	@Autowired
	private SurveyOptionService surveyOptionService;

	@GetMapping("/getall")
	public PageResponse<GetAllSurveyOptionResponse> getAll() {
		return surveyOptionService.getAll();
	}

}
