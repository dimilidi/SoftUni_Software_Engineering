DELIMITER $$
CREATE FUNCTION udf_average_salary_by_position_name (name VARCHAR(40))
    RETURNS DECIMAL(19, 2)
    DETERMINISTIC
BEGIN
	DECLARE average_salary_by_position DECIMAL(19, 2);

SELECT AVG(w.salary) INTO average_salary_by_position
FROM workers w
         JOIN positions p ON p.id = w.position_id
WHERE p.name = name;

RETURN average_salary_by_position;
END$$

DELIMITER ;


SELECT p.name, udf_average_salary_by_position_name('Forester') as position_average_salary FROM positions p
WHERE p.name = 'Forester';