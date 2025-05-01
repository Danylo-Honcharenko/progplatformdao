INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_TEACHER');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO users (id, first_name, last_name, password, role_id, created)
VALUES (1, 'David', 'Menchuk', '123456789', 1, '2024-09-11 17:09:10');

INSERT INTO users (id, first_name, last_name, password, role_id, created)
VALUES (2, 'Dima', 'Valentinov', '123456789', 1, '2024-09-11 17:09:10');

INSERT INTO courses (id, name, description, created)
VALUES (1, 'C++', 'This is...', '2024-8-05 12:10:44');

INSERT INTO courses (id, name, description, created)
VALUES (2, 'Python', 'This is...', '2024-8-05 12:10:44');

INSERT INTO courses (id, name, description, created)
VALUES (3, 'Java', 'This is...', '2024-8-05 12:10:44');

INSERT INTO user_course (user_id, course_id)
VALUES (1, 1);

INSERT INTO user_course (user_id, course_id)
VALUES (1, 2);

INSERT INTO topics (id, name, description, created, course_id)
VALUES (1, 'Pointers', 'This is...', '2024-04-12 09:12:44', 1);

INSERT INTO topics (id, name, description, created, course_id)
VALUES (2, 'Variable', 'This is...', '2024-04-12 09:12:44', 1);

INSERT INTO exercises (id, name, description, created, topic_id)
VALUES (1, 'Create app', 'This is...', '2024-05-12 09:12:44', 1);

INSERT INTO answers_to_exercise (id, text, file_path, user_id, created)
VALUES (1, 'This is...', 'C:\\folder\\test.exe', 1, '2024-10-12 09:12:44');

INSERT INTO exercises_answer (exercise_id, answer_id)
VALUES (1, 1);

INSERT INTO exercises (id, name, description, created, topic_id)
VALUES (2, 'Deploy APP', 'This is...', '2024-05-12 09:12:44', 1);

INSERT INTO tests (id, name, assessment, created, exercise_id)
VALUES (1, 'Pointers', 12, '2024-05-12 09:12:44', 1);

INSERT INTO user_test (user_id, test_id)
VALUES (1, 1);

INSERT INTO user_test (user_id, test_id)
VALUES (2, 1);

INSERT INTO answers_to_question (id, text, created)
VALUES (1, 'Text question 1', '2024-05-12 09:12:44');

INSERT INTO answers_to_question (id, text, created)
VALUES (2, 'Text question 2', '2024-05-12 09:12:44');

INSERT INTO answers_to_question (id, text, created)
VALUES (3, 'Text question 3', '2024-05-12 09:12:44');

INSERT INTO questions (id, text, correct_answer_id, created, test_id)
VALUES (1, 'What is?', 2, '2024-05-12 09:12:44', 1);

INSERT INTO questions (id, text, correct_answer_id, created, test_id)
VALUES (2, 'What is? 2', 3, '2024-05-12 09:12:44', 1);

INSERT INTO question_answer (question_id, answer_id)
VALUES (1, 1);

INSERT INTO question_answer (question_id, answer_id)
VALUES (1, 2);

INSERT INTO question_answer (question_id, answer_id)
VALUES (1, 3);