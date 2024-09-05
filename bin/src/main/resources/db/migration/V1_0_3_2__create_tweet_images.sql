CREATE TABLE tweet_images (
	id BINARY(16) PRIMARY KEY NOT NULL,
	image_path VARCHAR(200) NOT NULL,
	tweet_id BINARY(16) NOT NULL,
	CONSTRAINT tweet_images_fk
		FOREIGN KEY (tweet_id)
		REFERENCES tweets (id)
);