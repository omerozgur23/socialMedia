CREATE TABLE comment_likes (
	user_id BINARY(16) NOT NULL,
	comment_id BINARY(16) NOT NULL,
	CONSTRAINT comment_likes_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	CONSTRAINT comment_likes_comment_fk
		FOREIGN KEY (comment_id)
		REFERENCES comments (id)
);
