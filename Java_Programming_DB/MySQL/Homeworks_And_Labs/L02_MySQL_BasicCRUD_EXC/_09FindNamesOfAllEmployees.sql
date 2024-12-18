SELECT CONCAT_WS(' ',first_name, middle_name, last_name)
    AS 'Full Name'
FROM soft_uni.employees
WHERE salary IN(25000, 14000, 12500, 23600)
ORDER BY employee_id;