/*
05.	Airplanes
Extract from the airlines_db, info about the airplanes.
Order the results by cost in descending order and then by id in descending.
*/



USE airlines_db;

SELECT a.id, a.model, a.passengers_capacity, a.tank_capacity, a.cost
FROM airplanes a
ORDER BY a.cost DESC, a.id DESC;