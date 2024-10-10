/*
06.	Birthdate
Write a query that returns: first_name, last_name, birthdate and review from clients.
Filter clients which don't have card and their birthdate is between 1978 and  1993 inclusive.
Show only the first 5 results and order them descending by last_name, then by id ascending.
Required Columns
•	first_name
•	last_name
•	birthdate
•	review
*/

USE restaurant_db;

SELECT first_name, last_name, birthdate, review
FROM clients
WHERE card IS NULL
    AND YEAR(birthdate) between 1978 and  1993
ORDER BY last_name DESC, id
LIMIT 5;
