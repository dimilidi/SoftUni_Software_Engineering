SELECT c.name, COUNT(*)  AS instructors_count
FROM instructors_driving_schools ids
         LEFT JOIN driving_schools ds  ON ids.driving_school_id = ds.id
         JOIN  cities c  ON ds.city_id = c.id
GROUP BY c.name
ORDER BY instructors_count DESC;