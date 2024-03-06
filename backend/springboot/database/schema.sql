BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS custom_role;
DROP TABLE IF EXISTS custom_role_user;
DROP TABLE IF EXISTS courses;

CREATE TABLE IF NOT EXISTS users
(
    user_id  SERIAL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password_hash character varying(200) NOT NULL,
    role character varying(50) NOT NULL,
    access_code TEXT[],
    status smallint DEFAULT 1,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS custom_role (
	role_id SERIAL,
	role_name varchar(50) NOT NULL,
	CONSTRAINT PK_role PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS custom_role_user (
	role_id INT NOT NULL,
	user_id INT NOT NULL,
	CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES custom_role (role_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS courses
(
    course_id SERIAL,
    course_name VARCHAR(50) NOT NULL,
    image_key VARCHAR(50) NOT NULL,
    description VARCHAR[],
    price DECIMAL NOT NULL,
    UNIQUE(course_name),
    CONSTRAINT pk_course PRIMARY KEY (course_id)
)

COMMIT TRANSACTION;