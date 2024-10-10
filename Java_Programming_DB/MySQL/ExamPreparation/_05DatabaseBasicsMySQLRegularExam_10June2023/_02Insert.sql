/*
02.	Insert
You will have to insert records of data into the courses table, based on the courses table.
For courses with id equal or lesser than 5, insert data in the course table with the following values:
•	name – set it to the teacher name followed by white space and then "course"
(teacher_name + " " + "course")
•	duration_hours – set it to the total number of characters from the course name and the result divided by 10.
•	start_date – set it to the start date of the course but 5 days later.
•	teacher_name – set it to the teacher name but reversed.
•	description – set it to "Course " followed by the teacher name and the description but reversed.
 ("Course " + teacher_name + description_reversed)
•	university¬_id – set it to the day of the start date of the original course.
*/
USE universities_db;

INSERT INTO courses(name, courses.duration_hours, start_date, teacher_name, description, university_id)
    SELECT
        CONCAT_WS(' ', c.teacher_name, 'course'),
        CHAR_LENGTH(c.name) / 10,
        DATE_ADD(c.start_date, INTERVAL 5 DAY) ,
        REVERSE(c.teacher_name),
        CONCAT('Course ', c.teacher_name, REVERSE(c.description)),
        (DAY(start_date))
FROM courses c
WHERE id <= 5;