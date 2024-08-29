package com.socialMedia.dtos;

import com.socialMedia.core.utilities.exceptions.Messages;

import lombok.Data;

@Data
public class SuccessResponse {

	private String message = Messages.SUCCESSFULLY;
}