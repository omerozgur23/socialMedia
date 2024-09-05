CREATE TABLE user_tweets (
	id BINARY(16) PRIMARY KEY NOT NULL,
	user_id BINARY(16) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	is_deleted BOOLEAN NOT NULL,
	CONSTRAINT user_tweets_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT user_tweets_tweet_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);