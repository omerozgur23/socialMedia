CREATE TABLE confirmation_tokens (
	id BINARY(16) PRIMARY KEY NOT NULL,
	token VARCHAR(200) UNIQUE NOT NULL,
	created_at DATETIME NOT NULL,
	expires_at DATETIME NOT NULL,
	confirmed_at DATETIME NULL,
	user_id BINARY(16) NOT NULL,
	CONSTRAINT token_user_fk
		FOREIGN KEY (user_id)
		REFERENCES users (id)
);