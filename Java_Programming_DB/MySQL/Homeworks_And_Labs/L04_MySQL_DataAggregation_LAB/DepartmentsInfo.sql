SELECT department_id, COUNT(id) AS 'Number of employees'
FROM restaurant_su.employees
GROUP BY department_id
ORDER BY department_id;