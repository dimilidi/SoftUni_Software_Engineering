/*
05.	Cities
Extract from the universities_db system database, info about the cities.
Order the results by population in descending order;
Required Columns
•	id
•	name
•	population
•	country_id
  */

USE universities_db;

SELECT *
FROM cities
ORDER BY population DESC;