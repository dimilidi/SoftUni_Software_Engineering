DELIMITER $$

CREATE PROCEDURE usp_get_employees_from_town(town VARCHAR(250))
BEGIN
SELECT first_name, last_name
FROM employees
         JOIN addresses a ON a.address_id = employees.address_id
         JOIN towns t ON t.town_id = a.town_id
WHERE t.name = town
ORDER BY first_name, last_name, employee_id;
END$$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');