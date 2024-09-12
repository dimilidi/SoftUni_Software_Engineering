/* Find projects on which employees work*/
/* employees <-> projects - n : m */

SELECT
    e.employee_id,
    e.first_name,
    e.last_name,
    ep.project_id,
    p.name
FROM employees e
         JOIN employees_projects ep ON ep.employee_id = e.employee_id
         JOIN projects p ON ep.project_id = p.project_id
ORDER BY e.employee_id;
