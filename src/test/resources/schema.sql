CREATE TABLE IF NOT EXISTS `roles` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS `users` (
    `id` INT PRIMARY KEY,
    `first_name` VARCHAR(100),
    `last_name` VARCHAR(100),
    `password` VARCHAR(255),
    `role_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS `courses` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(100),
    `description` TEXT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `user_course` (
    `user_id` INT,
    `course_id` INT,
    FOREIGN KEY (user_id) REFERENCES `users`(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS `topics` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(100),
    `description` TEXT,
    `course_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE IF NOT EXISTS `exercises` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(100),
    `description` TEXT,
    `assessment` INT,
    `topic_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (topic_id) REFERENCES topics(id)
);

CREATE TABLE IF NOT EXISTS `tests` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(100),
    `assessment` INT,
    `exercise_id` INT,
    `user_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);

CREATE TABLE IF NOT EXISTS `user_test` (
    `user_id` INT,
    `test_id` INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (test_id) REFERENCES tests(id)
);

CREATE TABLE IF NOT EXISTS `answers_to_question` (
    `id` INT PRIMARY KEY,
    `text` VARCHAR(255),
    `created` TIMESTAMP,
    `updated` TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `questions` (
    `id` INT PRIMARY KEY,
    `text` VARCHAR(150),
    `correct_answer_id` INT UNIQUE,
    `test_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (correct_answer_id) REFERENCES answers_to_question(id),
    FOREIGN KEY (test_id) REFERENCES tests(id)
);

CREATE TABLE IF NOT EXISTS `question_answer` (
    `question_id` INT,
    `answer_id` INT,
    FOREIGN KEY (question_id) REFERENCES questions(id),
    FOREIGN KEY (answer_id) REFERENCES answers_to_question(id)
);

CREATE TABLE IF NOT EXISTS `answers_to_exercise` (
    `id` INT PRIMARY KEY,
    `text` TEXT,
    `file_path` VARCHAR(255),
    `user_id` INT,
    `created` TIMESTAMP,
    `updated` TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS `exercises_answer` (
    `exercise_id` INT,
    `answer_id` INT,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id),
    FOREIGN KEY (answer_id) REFERENCES answers_to_exercise(id)
);