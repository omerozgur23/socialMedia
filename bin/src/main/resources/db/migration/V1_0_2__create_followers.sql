CREATE TABLE followers (
	following_user_id BINARY(16) NOT NULL,
	follower_user_id BINARY(16) NOT NULL,
	CONSTRAINT following_user_fk
		FOREIGN KEY (following_user_id)
		REFERENCES users (id),
	CONSTRAINT follower_user_fk
		FOREIGN KEY (follower_user_id)
		REFERENCES users (id)
);
