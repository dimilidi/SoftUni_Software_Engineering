/*
05.	Clients
Extract from the restaurant_db system database, info about the clients.
Order the results by birthdate in descending order and id in descending;
Required Columns
•	id (clients)
•	first_name
•	last_name
•	birthdate
•	card
•	review
*/

USE restaurant_db;

SELECT *
FROM clients
ORDER BY birthdate DESC, id DESC;