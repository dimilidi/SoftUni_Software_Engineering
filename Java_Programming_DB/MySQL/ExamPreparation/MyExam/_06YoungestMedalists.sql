/*
06.	Youngest medalists
Extract from the summer_olympics database full_name and age of the two athletes
with the minimum age who have won at least one medal.
Order the results by the athlete's id in ascending order.
Required Columns
•	full_name (first_name + " " + last_name)
•	age
*/


USE summer_olympics;

SELECT CONCAT(a.first_name, ' ', a.last_name) AS full_name, a.age
FROM athletes a
         JOIN disciplines_athletes_medals dam ON a.id = dam.athlete_id
GROUP BY a.id, a.age
ORDER BY a.age ASC, a.id ASC
LIMIT 2;