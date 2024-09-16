SELECT p.name, country_code, YEAR(established_on) as founded_in
FROM preserves p
    JOIN countries_preserves cp ON cp.preserve_id = p.id
    JOIN countries c ON cp.country_id = c.id
WHERE MONTH(p.established_on) = 5
ORDER BY p.established_on
    LIMIT 10;