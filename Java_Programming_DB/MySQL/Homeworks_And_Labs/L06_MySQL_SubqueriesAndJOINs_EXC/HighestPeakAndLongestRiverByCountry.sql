SELECT c.country_name, MAX(elevation) AS highest_peak_elevation, MAX(length) AS longest_river_length
FROM countries c
         JOIN mountains_countries mc ON mc.country_code = c.country_code
         JOIN peaks p ON p.mountain_id = mc.mountain_id
         JOIN countries_rivers cr ON cr.country_code = c.country_code
         JOIN rivers r ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, c.country_name
    LIMIT 5;