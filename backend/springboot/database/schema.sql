BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    user_id  SERIAL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password_hash character varying(200) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)
);

COMMIT TRANSACTION;