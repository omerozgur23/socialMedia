package com.socialMedia.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.business.abstracts.SurveyOptionService;
import com.socialMedia.core.utilities.config.mapper.ModelMapperService;
import com.socialMedia.core.utilities.exceptions.BusinessException;
import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dataAccess.SurveyOptionRepository;
import com.socialMedia.dtos.PageResponse;
import com.socialMedia.dtos.surveyOption.CreateSurveyOptionRequest;
import com.socialMedia.dtos.surveyOption.GetAllSurveyOptionResponse;
import com.socialMedia.entities.Survey;
import com.socialMedia.entities.SurveyOption;

@Service
public class SurveyOptionManager implements SurveyOptionService {

	@Autowired
	private SurveyOptionRepository surveyOptionRepository;

	@Autowired
	private ModelMapperService modelMapper;

	@Override
	public PageResponse<GetAllSurveyOptionResponse> getAll() {
		List<SurveyOption> surveyOptions = surveyOptionRepository.findAll();
		List<GetAllSurveyOptionResponse> response = surveyOptions.stream()
				.map(surveyOption -> modelMapper.forResponse().map(surveyOption, GetAllSurveyOptionResponse.class))
				.toList();
		int count = response.size();
		return new PageResponse<GetAllSurveyOptionResponse>(count, response);
	}

	@Override
	public SurveyOption getSurveyOption(UUID id) {
		return surveyOptionRepository.findById(id)
				.orElseThrow(() -> new BusinessException(Messages.SURVEY_OPTION_NOT_FOUND));
	}

	@Override
	public List<SurveyOption> getSurveyOptionsWithSurveyId(UUID surveyId) {
		return surveyOptionRepository.findBySurveyId(surveyId)
				.orElseThrow(() -> new BusinessException(Messages.SURVEY_OPTION_NOT_FOUND));
	}

	@Override
	public List<SurveyOption> create(Survey survey, List<CreateSurveyOptionRequest> request) {
		List<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();
		for (CreateSurveyOptionRequest surveyOptionsRequest : request) {
			SurveyOption surveyOption = modelMapper.forRequest().map(surveyOptionsRequest, SurveyOption.class);
			surveyOption.setSurvey(survey);
			surveyOptions.add(surveyOption);
		}
		surveyOptionRepository.saveAll(surveyOptions);
		return surveyOptions;
	}

	@Override
	public void delete(UUID surveyId) {
		List<SurveyOption> surveyOptions = getSurveyOptionsWithSurveyId(surveyId);
		surveyOptionRepository.deleteAll(surveyOptions);
	}

}
