CREATE TABLE survey_answers (
	id BINARY(16) PRIMARY KEY NOT NULL,
	user_id BINARY(16) NOT NULL,
	survey_id BINARY(16) NOT NULL,
	survey_option_id BINARY(16) NOT NULL,
	CONSTRAINT survey_answer_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT surveys_answer_survey_fk
		FOREIGN KEY (survey_id)
		REFERENCES surveys (id),
    CONSTRAINT survey_answer_survey_option_fk
		FOREIGN KEY (survey_option_id)
		REFERENCES survey_options (id)
);