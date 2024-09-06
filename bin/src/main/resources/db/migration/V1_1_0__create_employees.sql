CREATE TABLE employees (
	id BINARY(16) PRIMARY KEY NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	birth_date DATETIME NOT NULL,
	password VARCHAR(16) NOT NULL,
	role_id BINARY(16) NOT NULL,
	CONSTRAINT employees_role_fk
		FOREIGN KEY (role_id)
		REFERENCES roles (id)
);