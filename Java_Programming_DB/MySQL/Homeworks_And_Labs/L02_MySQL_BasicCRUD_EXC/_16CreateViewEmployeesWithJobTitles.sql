CREATE VIEW soft_uni.v_employees_job_titles AS
SELECT CONCAT_WS(' ', first_name, middle_name, last_name) AS 'full_name', job_title
FROM soft_uni.employees;