/*
10.	Average grades
Create a user defined function with the name udf_average_alumni_grade_by_course_name(course_name VARCHAR(60))
that receives a course name and returns the average grades of the grades from those students that are graduated.
Required Columns
•	course_name
•	average_alumni_grade (udf_average_alumni_grade_by_course_name)
*/

USE universities_db;

DELIMITER $$

CREATE FUNCTION udf_average_alumni_grade_by_course_name(course_name VARCHAR(60))
    RETURNS DECIMAL(19,2)
    DETERMINISTIC

BEGIN

    RETURN (SELECT AVG(sc.grade)
            FROM students_courses sc
                     JOIN students s ON s.id = sc.student_id
                     JOIN courses c ON c.id = sc.course_id
            WHERE c.name = course_name
              AND s.is_graduated
            GROUP BY sc.course_id);

END $$

DELIMITER ;


SELECT c.name, udf_average_alumni_grade_by_course_name('Quantum Physics') as average_alumni_grade FROM courses c
WHERE c.name = 'Quantum Physics';
