CREATE TABLE comment_likes (
	id BINARY(16) PRİMARY KEY NOT NULL,
	user_id BINARY(16) NOT NULL,
	comment_id BINARY(16) NOT NULL,
	comment_type ENUM('TWEET_COMMENT', 'COMMENT_REPLY') NOT NULL,
	CONSTRAINT comment_likes_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id),
	 CHECK ( 
        (comment_type = 'TWEET_COMMENT' AND comment_id IN (SELECT id FROM tweet_comments)) OR
        (comment_type = 'COMMENT_REPLY' AND comment_id IN (SELECT id FROM comment_replies))
    )
);
