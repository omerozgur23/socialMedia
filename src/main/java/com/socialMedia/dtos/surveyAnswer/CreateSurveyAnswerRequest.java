package com.socialMedia.dtos.surveyAnswer;

import java.util.UUID;

import com.socialMedia.core.utilities.exceptions.Messages;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSurveyAnswerRequest {

	@NotNull(message = Messages.SURVEY_ID_CANNOT_BE_NULL)
	private UUID survey;

	@NotNull(message = Messages.SURVEY_OPTION_ID_CANNOT_BE_NULL)
	private UUID surveyOption;
}
