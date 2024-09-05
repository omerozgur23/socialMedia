CREATE TABLE messages (
	id BINARY(16) PRIMARY KEY NOT NULL,
	sending_user_id BINARY(16) NOT NULL,
	recipient_user_id BINARY(16) NOT NULL,
	message_content VARCHAR(10000) NOT NULL,
	sending_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	is_readed BOOLEAN NOT NULL,
	CONSTRAINT messages_sending_fk
		FOREIGN KEY (sending_user_id)
		REFERENCES users (id),
	CONSTRAINT messages_recipient_fk
		FOREIGN KEY (recipient_user_id)
		REFERENCES users (id)
);
