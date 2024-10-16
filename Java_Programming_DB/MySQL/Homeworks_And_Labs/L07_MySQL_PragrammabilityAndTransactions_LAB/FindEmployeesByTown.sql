DELIMITER $$

CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
RETURN (
    SELECT COUNT(*)
    FROM employees AS e
             JOIN addresses AS a ON e.address_id = a.address_id
             JOIN towns t on t.town_id = a.town_id
    WHERE t.name = town_name
);
END$$

DELIMITER ;

SELECT ufn_count_employees_by_town('Sofia');