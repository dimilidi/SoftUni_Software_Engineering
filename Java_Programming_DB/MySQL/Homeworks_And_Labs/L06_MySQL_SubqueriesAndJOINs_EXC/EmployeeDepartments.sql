SELECT
    e.employee_id,
    e.first_name,
    e.salary,
    d.name AS department_name
FROM employees e
         JOIN departments d ON d.department_id = e.department_id
WHERE salary > 15000
ORDER BY d.department_id DESC
    LIMIT 5;
