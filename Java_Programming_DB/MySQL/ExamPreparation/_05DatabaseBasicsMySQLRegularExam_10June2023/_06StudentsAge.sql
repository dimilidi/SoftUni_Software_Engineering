/*
06.	Students age
Write a query that returns: first_name, last_name, age, phone and email from table students.
Filter students with an age equal or higher than 21.
Order the results descending by first_name, then by email ascending,
then by id in ascending order and show only the first 10 results.
    Required Columns
    •	first_name
    •	last_name
    •	age
    •	phone
    •	email
*/
USE universities_db;

SELECT first_name, last_name, age, phone, email
FROM students
WHERE age >= 21
ORDER BY first_name DESC, email, id
    LIMIT 10;