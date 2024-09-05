CREATE TABLE comments (
	id BINARY(16) PRIMARY KEY NOT NULL,
	comment VARCHAR(280) NOT NULL,
	user_id BINARY(16) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT comment_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT comment_tweet_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);
