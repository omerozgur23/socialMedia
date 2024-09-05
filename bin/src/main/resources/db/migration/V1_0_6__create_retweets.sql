CREATE TABLE retweets (
	user_id BINARY(16) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	CONSTRAINT retweets_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT retweets_tweet_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);
