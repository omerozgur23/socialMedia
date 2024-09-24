CREATE TABLE tweet_quotes (
	id BINARY(16) PRIMARY KEY NOT NULL,
	user_id BINARY(16) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	tweet_quote_id BINARY(16) NOT NULL,
	CONSTRAINT quote_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT quote_tweet_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id),
	CONSTRAINT quote_tweet_quote_fk
		FOREIGN KEY (tweet_quote_id)
		REFERENCES tweets (id)
);
