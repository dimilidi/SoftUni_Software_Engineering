/*
08.	Students count
Every university has courses with students.
The directors of every university want to know the total number of students assigned to courses.
Extract from the database the students_count (total number of assigned students) in each university
and the corresponding university_name.
Get only those universities with students_count equal or greater than 8.
Order the results descending by students_count and then by university_name in descending order.
 Required Columns
•	students_count
•	university_name
*/

USE universities_db;

SELECT COUNT(*) AS students_count, u.name AS university_name
FROM students_courses sc
JOIN courses c ON c.id = sc.course_id
JOIN universities u ON u.id = c.university_id
GROUP BY u.name
HAVING COUNT(sc.student_id) >= 8
ORDER BY students_count DESC, u.name DESC;