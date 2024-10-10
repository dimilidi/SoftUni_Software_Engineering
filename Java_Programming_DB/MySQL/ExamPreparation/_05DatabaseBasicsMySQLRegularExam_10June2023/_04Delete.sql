/*
04.	Delete
There are some minor bugs in the system and some universities didn’t send the correct information.
Delete all universities for which we don’t have information about the number of staff.
*/

USE universities_db;

DELETE FROM universities
WHERE number_of_staff IS NULL;

