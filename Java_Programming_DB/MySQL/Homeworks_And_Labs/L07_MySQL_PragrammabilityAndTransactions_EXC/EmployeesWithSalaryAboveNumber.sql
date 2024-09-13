DELIMITER $$

CREATE PROCEDURE usp_get_employees_salary_above(salary DECIMAL(12, 4))
BEGIN
SELECT e.first_name, e.last_name
FROM employees e
WHERE e.salary >= salary
ORDER BY first_name, last_name, employee_id;
END$$

DELIMITER ;

CALL usp_get_employees_salary_above(35000);