CREATE TABLE comment_replies (
	id BINARY(16) PRIMARY KEY NOT NULL,
	comment_reply VARCHAR(280) NOT NULL,
	user_id BINARY(16) NOT NULL,
	tweet_comment_id BINARY(16) NOT NULL,
	created_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	deleted_date DATETIME NULL,
	is_deleted BOOLEAN DEFAULT FALSE NULL,
	CONSTRAINT comment_replies_comment_fk
		FOREIGN KEY (comment_id)
		REFERENCES comments (id)
);
