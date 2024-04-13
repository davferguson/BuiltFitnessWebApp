BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
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

CREATE TABLE IF NOT EXISTS courses
(
    course_id SERIAL,
    course_name VARCHAR(50) NOT NULL,
    image_key VARCHAR(50) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    price DECIMAL NOT NULL,
    UNIQUE(course_name),
    CONSTRAINT pk_course PRIMARY KEY (course_id)
);

COMMIT TRANSACTION;