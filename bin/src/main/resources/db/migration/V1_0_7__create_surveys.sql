CREATE TABLE surveys (
	id BINARY(16) PRIMARY KEY NOT NULL,
	creator_user_id BINARY(16) NOT NULL,
	evaluating_user_id BINARY(16) NULL,
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	deleted_date DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
	is_finished BOOLEAN NOT NULL,
	CONSTRAINT surveys_creator_fk
		FOREIGN KEY (creator_user_id)
		REFERENCES users (id),
	CONSTRAINT surveys_evaluating_fk
		FOREIGN KEY (evaluating_user_id)
		REFERENCES users (id)
);
