/*
04.	Delete
Delete all athletes, older than 35 years.
*/

USE summer_olympics;

DELETE FROM athletes
WHERE age > 35;