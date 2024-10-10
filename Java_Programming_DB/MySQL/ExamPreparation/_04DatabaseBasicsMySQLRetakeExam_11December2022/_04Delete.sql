/*
04.	Delete
Delete all flights that don't have passengers.
*/


USE airlines_db;

DELETE FROM flights f
WHERE f.id NOT IN (
    SELECT fp.flight_id
    FROM flights_passengers fp
);