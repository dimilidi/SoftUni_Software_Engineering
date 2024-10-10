/*
11.	Delay flight
A flight has been delayed.
Your task is to find the flight and
change the delay status to true and
the departure time by 30 minutes.
Create a stored procedure udp_delay_flight which accepts the following parameters:
•	code VARCHAR(50)
*/

USE airlines_db;

DELIMITER $$
CREATE PROCEDURE udp_delay_flight(code VARCHAR(50))
BEGIN
    UPDATE flights f
    SET f.has_delay = 1, f.departure = DATE_ADD(departure, INTERVAL 30 MINUTE)
    WHERE f.flight_code = code;
END$$;

DELIMITER ;

CALL udp_delay_flight('ZP-782');