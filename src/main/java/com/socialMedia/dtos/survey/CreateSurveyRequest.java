package com.socialMedia.dtos.survey;

import java.util.List;

import com.socialMedia.core.utilities.exceptions.Messages;
import com.socialMedia.dtos.surveyOption.CreateSurveyOptionRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyRequest {

	@NotBlank(message = Messages.SURVEY_TITLE_CANNOT_BE_BLANK)
	@Size(max = 200, message = Messages.SURVEY_TITLE_CHARACTER_LIMIT_EXCEEDED_200)
	private String title;

	@NotBlank(message = Messages.SURVEY_FINISHED_DATE_CANNOT_BE_BLANK)
	private String finishedDate;

	@Size(min = 2, max = 4, message = Messages.SURVEY_MUST_HAVE_BETWEEN_2_AND_4_OPTIONS)
	private List<CreateSurveyOptionRequest> surveyOptions;
}
