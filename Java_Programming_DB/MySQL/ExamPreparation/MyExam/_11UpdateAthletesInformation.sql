/*
11.	Update athlete's information
Create a stored procedure udp_first_name_to_upper_case which accepts the following parameters:
•	letter (CHAR(1))
Update the information in column first_name from the athletes table by
capitalising all the letters in it.
Do it only for the athletes whose first name ends with the given letter.
(The given letter will always be lowercase)
*/

USE summer_olympics;

DELIMITER $$

CREATE PROCEDURE udp_first_name_to_upper_case(IN letter CHAR(1))
BEGIN
    UPDATE athletes
    SET first_name = UPPER(first_name)
    WHERE SUBSTRING(first_name, -1) = letter;
END$$

DELIMITER ;
