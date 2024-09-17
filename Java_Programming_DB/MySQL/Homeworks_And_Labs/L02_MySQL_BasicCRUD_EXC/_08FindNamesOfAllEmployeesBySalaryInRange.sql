SELECT first_name, last_name, job_title
FROM soft_uni.employees
WHERE salary BETWEEN 20000 AND 30000
ORDER BY employee_id;