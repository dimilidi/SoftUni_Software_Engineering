SELECT department_id, ROUND(MIN(salary), 2) AS 'Min salary'
FROM restaurant_su.employees
GROUP BY department_id
HAVING `Min Salary` > 800;