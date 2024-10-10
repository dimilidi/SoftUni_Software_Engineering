/*
09.	Parts of the day
From the database extract the flight_code and departure.
Order the results descending by flight_code.
Required Columns
•	flight_code
•	departure
•	day_part (
Morning is from 5AM to 11:59AM,
Afternoon is from 12PM to 4:59PM,
Evening is from 5PM to 8:59PM and
Night is from 9PM to 4:59AM)
*/

USE airlines_db;

SELECT flight_code, departure,
       (CASE
            WHEN TIME(departure) BETWEEN '05:00:00' AND '11:59:59' THEN 'Morning'
            WHEN TIME(departure) BETWEEN '12:00:00' AND '16:59:59' THEN 'Afternoon'
            WHEN TIME(departure) BETWEEN '17:00:00' AND '20:59:59' THEN 'Evening'
            WHEN TIME(departure) BETWEEN '21:00:00' AND '23:59:59' THEN 'Night'
            WHEN TIME(departure) BETWEEN '00:00:00' AND '04:59:00' THEN 'Night'
        END) AS day_part
FROM flights
ORDER BY flight_code DESC;