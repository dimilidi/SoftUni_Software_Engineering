/*
03.	Update
There was a mistake at the Armenian airport with the airplanes.
You must change the airplanes for all flights that departure from Armenia.
Raise the airplane_id by 1 for all flights flying from Armenia.
*/


USE airlines_db;

UPDATE flights f
JOIN countries c ON c.id = f.departure_country
SET f.airplane_id = f.airplane_id + 1
WHERE c.name LIKE 'Armenia';


