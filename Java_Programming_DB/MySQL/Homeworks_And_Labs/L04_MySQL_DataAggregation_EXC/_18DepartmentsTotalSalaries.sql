SELECT
    department_id,
    FORMAT(SUM(salary), 2) AS total_salary
FROM soft_uni_da.employees
GROUP BY department_id
ORDER BY department_id;