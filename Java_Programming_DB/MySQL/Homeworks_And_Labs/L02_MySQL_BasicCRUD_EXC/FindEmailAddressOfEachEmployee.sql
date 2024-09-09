SELECT CONCAT(first_name,'.', last_name, '@', 'softuni.bg')
    AS 'full_ email_address'
FROM soft_uni.employees
ORDER BY employee_id;