CREATE SEQUENCE users_id_seq;

CREATE TABLE IF NOT EXISTS users(
id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('users_id_seq') ,
email VARCHAR(255) NOT NULL,
birthdate TIMESTAMP NOT NULL
);

ALTER SEQUENCE users_id_seq OWNED BY users.id;