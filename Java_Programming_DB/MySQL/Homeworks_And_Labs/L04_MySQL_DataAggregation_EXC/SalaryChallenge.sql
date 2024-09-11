SELECT first_name, last_name, department_id
FROM soft_uni_da.employees
WHERE salary >
	(SELECT AVG(salary)
    FROM soft_uni_da.employees AS e
    WHERE e.department_id = employees.department_id)
ORDER BY department_id, employee_id
LIMIT 10;