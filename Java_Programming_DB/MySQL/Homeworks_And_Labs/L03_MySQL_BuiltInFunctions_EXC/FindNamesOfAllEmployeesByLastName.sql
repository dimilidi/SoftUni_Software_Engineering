SELECT first_name, last_name
FROM soft_uni.employees
WHERE last_name LIKE('%ei%')
ORDER BY employee_id;