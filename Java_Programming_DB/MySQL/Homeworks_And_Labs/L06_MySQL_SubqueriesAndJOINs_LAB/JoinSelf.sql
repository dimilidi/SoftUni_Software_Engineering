SELECT
    e.employee_id,
    e.first_name,
    e.last_name,
    m.employee_id AS manager_id,
    m.first_name AS manager_first_name,
    m.last_name AS manager_last_name
FROM employees e
         JOIN employees m ON e.manager_id = m.employee_id;