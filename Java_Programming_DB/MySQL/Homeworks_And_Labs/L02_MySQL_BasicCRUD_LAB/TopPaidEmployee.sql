CREATE VIEW top_paid_employee AS
SELECT *
FROM hotel.employees
ORDER BY salary DESC
    LIMIT 1;
