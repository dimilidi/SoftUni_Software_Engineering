DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
    START TRANSACTION;
    IF ((SELECT COUNT(*) FROM employees WHERE employee_id = id) <> 1) THEN
       ROLLBACK;
    ELSE
        UPDATE employees
        SET salary = salary * 1.05
        WHERE employee_id = id;
        COMMIT;
    END IF;
END$$
DELIMITER ;