package com.socialMedia.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "survey_options")
public class SurveyOption {

	@Id
	@GeneratedValue
	private UUID id;

	private String desciription;

	@ManyToOne
	@JoinColumn(name = "survey_id")
	private Survey survey;
}
