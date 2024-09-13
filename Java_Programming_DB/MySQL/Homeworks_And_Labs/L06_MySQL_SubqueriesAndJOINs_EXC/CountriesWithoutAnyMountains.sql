SELECT  COUNT(c.country_code)
FROM countries c
         LEFT JOIN mountains_countries mc ON c.country_code = mc.country_code
WHERE mc.mountain_id IS NULL;