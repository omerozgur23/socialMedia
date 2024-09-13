package com.socialMedia.dtos.surveyOption;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyOptionRequest {

	@NotBlank(message = Messages.SURVEY_OPTIONS_DESCRIPTION_CANNOT_BE_BLANK)
	@Size(max = 25, message = Messages.SURVEY_OPTION_DESCRIPTION_MUST_BE_25_CHARACTERS_OR_LESS)
	private String description;
}
