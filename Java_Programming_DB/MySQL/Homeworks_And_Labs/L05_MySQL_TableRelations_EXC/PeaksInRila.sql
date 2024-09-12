USE geography;

SELECT * FROM mountains;
SELECT * FROM peaks;

SELECT m.mountain_range, p.peak_name, p.elevation AS peak_elevation
FROM mountains m
         JOIN peaks p ON m.id = p.mountain_id
where mountain_range = 'Rila'
ORDER BY peak_elevation DESC;
