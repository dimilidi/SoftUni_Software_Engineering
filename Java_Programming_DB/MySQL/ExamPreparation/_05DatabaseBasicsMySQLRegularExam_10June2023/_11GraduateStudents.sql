/*
11.	Graduate students
Create a stored procedure udp_graduate_all_students_by_year which accepts the following parameters:
•	year_started INT
Extracts data about all courses that started in the given year,
find the assigned students and change their graduated status to true.
*/

USE universities_db;

DELIMITER  $$
CREATE PROCEDURE udp_graduate_all_students_by_year(year_started INT)
BEGIN
    UPDATE students s
    SET s.is_graduated = 1
    WHERE s.id IN (
        SELECT sc.student_id
        FROM courses c
                 JOIN students_courses sc ON sc.course_id = c.id
        WHERE YEAR(c.start_date) = year_started
    );
END $$

DELIMITER ;


CALL udp_graduate_all_students_by_year(2017);