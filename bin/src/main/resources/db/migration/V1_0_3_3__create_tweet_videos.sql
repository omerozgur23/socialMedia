CREATE TABLE tweet_videos (
	id BINARY(16) PRIMARY KEY NOT NULL,
	video_path VARCHAR(200) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	CONSTRAINT tweet_videos_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);