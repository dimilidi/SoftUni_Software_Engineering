/*
05.	Countries without athletes
Write a query that returns the id and name from table countries.
Filter only the countries that do not have athletes at this Olympics.
Show only the first 15 (fifteen) results.
Order the results descending by country name.
Required Columns
•	id (country)
•	name (country)
*/

USE summer_olympics;

SELECT c.id, c.name
FROM countries c
         LEFT JOIN athletes a ON c.id = a.country_id
WHERE a.id IS NULL
ORDER BY c.name DESC
LIMIT 15;
