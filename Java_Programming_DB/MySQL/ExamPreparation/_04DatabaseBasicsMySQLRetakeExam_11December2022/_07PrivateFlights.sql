/*
07. Private flights
Some passengers don’t have a booked flight but want to travel in private.
Write a query that returns:
flight_code, full_name and country for all passengers that don’t have a booked flight.
To find their private flight_code you must take
the first 2 letters of their last name in upper case followed by their country id.
Order by country_id in ascending order.
Required Columns
•	flight_code (first 2 characters from last name + country_id)
•	full_name (first_name + " " + last_name)
•	country_id

*/

USE airlines_db;

SELECT CONCAT(UPPER(LEFT(p.last_name, 2)), p.country_id)AS flight_code, CONCAT_WS(' ',p.first_name, p.last_name) AS full_name, p.country_id
FROM passengers p
WHERE p.id NOT IN(
    SELECT fp.passenger_id
    FROM flights_passengers fp
)
ORDER BY p.country_id;
