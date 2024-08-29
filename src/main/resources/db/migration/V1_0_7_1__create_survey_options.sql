CREATE TABLE survey_options (
	id BINARY(16) NOT NULL,
	description VARCHAR(200) NOT NULL,
	survey_id BINARY(16) NOT NULL,
	CONSTRAINT survey_options_survey_fk
		FOREIGN KEY (survey_id)
		REFERENCES surveys (id)
);
