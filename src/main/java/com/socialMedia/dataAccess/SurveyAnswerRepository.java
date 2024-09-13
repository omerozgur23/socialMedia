package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialMedia.entities.SurveyAnswer;

public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, UUID> {

	Optional<SurveyAnswer> findByUserIdAndSurveyId(UUID userId, UUID surveyId);

	Optional<List<SurveyAnswer>> findBySurveyId(UUID surveyId);
}
