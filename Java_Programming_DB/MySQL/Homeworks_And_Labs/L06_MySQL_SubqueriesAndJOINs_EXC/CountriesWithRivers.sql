SELECT c.country_name, r.river_name
FROM countries c
         LEFT JOIN countries_rivers cr ON c.country_code = cr.country_code
         LEFT JOIN rivers r ON r.id = cr.river_id
         JOIN continents cont ON cont.continent_code = c.continent_code
WHERE cont.continent_name = 'Africa'
ORDER BY c.country_name
    LIMIT 5;