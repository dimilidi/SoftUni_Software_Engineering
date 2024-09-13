SELECT
    country_code,
    mountain_range,
    p.peak_name,
    p.elevation
FROM peaks p
         JOIN mountains m ON p.mountain_id = m.id
         JOIN mountains_countries mc ON mc.mountain_id = m.id
WHERE mc.country_code = 'BG'AND p.elevation > 2835
ORDER BY elevation DESC;