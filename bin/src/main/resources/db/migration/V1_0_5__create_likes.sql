CREATE TABLE likes (
	user_id BINARY(16) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	CONSTRAINT likes_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT likes_tweet_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);
