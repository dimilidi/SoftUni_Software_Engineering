/*
10.	Find the total count of medals by country
Create a user-defined function with the name udf_total_medals_count_by_country (name VARCHAR(40))
that receives a country_name and
returns the total number of medals won by all athletes competing for that country.
Required Columns
•	country_name
•	count_of_medals
*/


USE summer_olympics;

DELIMITER $$

CREATE FUNCTION udf_total_medals_count_by_country(country_name VARCHAR(40))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE total_medals INT;

    SELECT COUNT(*) INTO total_medals
    FROM disciplines_athletes_medals dam
             JOIN athletes a ON dam.athlete_id = a.id
             JOIN countries c ON a.country_id = c.id
    WHERE c.name = country_name;

    RETURN total_medals;
END$$

DELIMITER ;
