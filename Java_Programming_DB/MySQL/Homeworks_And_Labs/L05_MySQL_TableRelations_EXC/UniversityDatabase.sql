CREATE DATABASE university;
USE university;

CREATE TABLE subjects
(
    subject_id   INT(11) AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(50)
);

CREATE TABLE majors
(
    major_id INT(11) AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(50)
);

CREATE TABLE students
(
    student_id     INT(11) AUTO_INCREMENT PRIMARY KEY,
    student_number VARCHAR(12),
    student_name   VARCHAR(50),
    major_id       INT(11),

    CONSTRAINT fk_students_majors
        FOREIGN KEY (major_id)
            REFERENCES majors (major_id)
);


CREATE TABLE payments
(
    payment_id    INT(11) AUTO_INCREMENT PRIMARY KEY,
    payment_date  DATE,
    payment_amount DECIMAL(8, 2),
    student_id    INT(11),

    CONSTRAINT fk_payments_students
        FOREIGN KEY (student_id)
            REFERENCES students (student_id)
);

CREATE TABLE agenda
(
    student_id INT(11),
    subject_id INT(11),

    CONSTRAINT pk_agenda
        PRIMARY KEY (student_id, subject_id),

    CONSTRAINT fk_agenda_students
        FOREIGN KEY (student_id)
            REFERENCES students (student_id),

    CONSTRAINT fk_agenda_subjects
        FOREIGN KEY (subject_id)
            REFERENCES subjects (subject_id)
);