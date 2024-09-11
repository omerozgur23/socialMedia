CREATE TABLE blocked_ones (
	obstructive_id BINARY(16) NOT NULL,
	blocked_id BINARY(16) NOT NULL,
	CONSTRAINT obstructive_user_fk
		FOREIGN KEY (obstructive_id)
		REFERENCES users (id),
	CONSTRAINT blocked_user_fk
		FOREIGN KEY (blocked_id)
		REFERENCES users (id)
);
