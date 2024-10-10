/*
10.	Number of flights
Create a user defined function with the name udf_count_flights_from_country(country VARCHAR(50))
that receives a country name and returns the total number of flights departing from that country.
Required Columns
•	flights_count (udf_count_flights_from_country)
*/

USE airlines_db;

/*Option1: using INTO*/
DELIMITER $$

CREATE FUNCTION udf_count_flights_from_country(country VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE flight_count INT;

        SELECT COUNT(*)
        INTO flight_count
        FROM flights f
            JOIN  countries c ON f.departure_country = c.id
        WHERE c.name = country;

    RETURN flight_count;
END$$

DELIMITER ;


/*Option2: direct return*/
DELIMITER $$

CREATE FUNCTION udf_count_flights_from_country(country VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    RETURN(
        SELECT COUNT(*)
        FROM flights f
            JOIN  countries c ON f.departure_country = c.id
        WHERE c.name = country);
END $$

DELIMITER ;

SELECT udf_count_flights_from_country('Brazil') AS 'flights_count';
SELECT udf_count_flights_from_country('Philippines') AS 'flights_count';