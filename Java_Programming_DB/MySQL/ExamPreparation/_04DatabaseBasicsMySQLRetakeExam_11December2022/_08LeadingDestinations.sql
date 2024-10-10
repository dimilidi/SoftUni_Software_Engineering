/*
08.	Leading destinations
Top leading magazine "Air Above" wants to write an article about the top destinations with over 20 booked tickets.
Extract from the database, the countries that have 20 or more booked tickets for flights as a destination country by passengers.
Order the results descending by booked_tickets.
Required Columns
•	name (country)
•	currency
•	booked_tickets (number of booked tickets from passengers to the country as a destination)
*/

USE airlines_db;

SELECT c.name, c.currency, COUNT(c.id) AS booked_tickets
FROM countries c
JOIN flights f ON f.destination_country = c.id
JOIN flights_passengers fp ON fp.flight_id = f.id
GROUP BY c.id
HAVING COUNT(c.id) >= 20
ORDER BY booked_tickets DESC;
