BEGIN TRANSACTION;

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Ab Trainer', 'abtrainer','Train your abs for adest fat burner|:|yeah abs boy get em|:|those abs are looking good', 19.99);

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Full Body', 'fullbody', 'ajld jkwdlod daskl okda|:|njkdaq ajksod nnw|:|ndw dasd dednasd ad', 27.99);

INSERT INTO courses (course_name, image_key, description, price)
VALUES('Fat Burner', 'fatburner', 'ajld jkwdlod daskl okda|:|njkdaq ajksod nnw|:|ndw dasd dednasd ad', 8.99);

COMMIT TRANSACTION;