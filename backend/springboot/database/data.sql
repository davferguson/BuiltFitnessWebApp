BEGIN TRANSACTION;

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Ab Trainer', 'abtrainer', ARRAY ['ajld jkwdlod daskl okda', 'njkdaq ajksod nnw', 'ndw dasd dednasd ad'], 19.99);

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Full Body', 'fullbody', ARRAY ['ajld jkwdlod daskl okda', 'njkdaq ajksod nnw', 'ndw dasd dednasd ad'], 27.99);

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Fat Burner', 'fatburner', ARRAY ['ajld jkwdlod daskl okda', 'njkdaq ajksod nnw', 'ndw dasd dednasd ad'], 8.99);

COMMIT TRANSACTION;