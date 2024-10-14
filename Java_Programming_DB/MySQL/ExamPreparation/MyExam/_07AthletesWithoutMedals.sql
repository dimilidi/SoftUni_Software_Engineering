/*
07.	Athletes without medals
Not all athletes manage to reach the dream ladder of honour.
Write a query that extracts (id, first_name, last_name) from the database all athletes
who have not won any medals.
Order by id in ascending order.
Required Columns
•	id (athlete)
•	first_name (athlete)
•	last_name (athlete)*/

USE summer_olympics;

SELECT a.id, a.first_name, a.last_name
FROM athletes a
         LEFT JOIN disciplines_athletes_medals dam ON a.id = dam.athlete_id
WHERE dam.medal_id IS NULL
ORDER BY a.id;
