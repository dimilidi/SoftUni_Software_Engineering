DELIMITER $$

CREATE PROCEDURE udp_increase_salaries_by_country(country_name VARCHAR(40))
BEGIN
UPDATE workers w
    JOIN countries_preserves cp ON cp.preserve_id = w.preserve_id
    JOIN countries c ON c.id = cp.country_id
    SET w.salary = w.salary * 1.05
WHERE country_name = c.name;
END$$

DELIMITER ;



CALL udp_increase_salaries_by_country ('Germany');

SELECT  w.first_name, w.salary
FROM workers w
         JOIN countries_preserves cp ON cp.preserve_id = w.preserve_id
         JOIN countries c ON c.id = cp.country_id
WHERE c.name = 'Germany';