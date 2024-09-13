package com.socialMedia.dataAccess;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialMedia.entities.SurveyOption;

@Repository
public interface SurveyOptionRepository extends JpaRepository<SurveyOption, UUID> {

	Optional<List<SurveyOption>> findBySurveyId(UUID surveyId);
}
