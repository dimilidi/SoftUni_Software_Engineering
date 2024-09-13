DELIMITER $$

CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(12,2))
    RETURNS VARCHAR(20)
    DETERMINISTIC
BEGIN
RETURN(
    SELECT
        CASE
            WHEN  salary < 30000 THEN 'Low'
            WHEN salary BETWEEN 30000 AND 50000 THEN 'Average'
            WHEN salary > 50000 THEN 'High'
            END AS salary_level
);
END$$

DELIMITER ;

SELECT ufn_get_salary_level(13500.00);
SELECT ufn_get_salary_level(43300.00);
SELECT ufn_get_salary_level(125500.00);