SELECT
    e.employee_id,
    e.job_title,
    e.address_id,
    a.address_text
FROM employees e
         JOIN addresses a ON e.address_id = a.address_id
ORDER BY address_id
    LIMIT 5;
SELECT
    e.first_name,
    e.last_name,
    t.name AS town,
    a.address_text
FROM employees e
         JOIN addresses a ON e.address_id = a.address_id
         JOIN towns t ON t.town_id = a.town_id
ORDER BY e.first_name, e.last_name
    LIMIT 5;