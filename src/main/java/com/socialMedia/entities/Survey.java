package com.socialMedia.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surveys")
public class Survey {

	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "creator_user_id")
	private User creatorUser;

//	deneme 
	@ManyToOne
	@JoinColumn(name = "evaluating_user_id")
	private User evaluatingUser;

	private String createDate;

	private String deleteDate;

	private boolean isFinished;

	@OneToMany(mappedBy = "survey")
	private List<SurveyOption> surveyOptions;

}
