SELECT i.first_name, i.last_name, COUNT(*)  AS students_count, c.name
FROM instructors i
         LEFT JOIN instructors_students istud ON istud.instructor_id = i.id
         JOIN instructors_driving_schools ids ON ids.instructor_id = i.id
         JOIN driving_schools ds ON ds.id = ids.driving_school_id
         JOIN cities c ON ds.city_id = c.id
GROUP BY i.id, c.name, i.first_name
HAVING students_count > 1
ORDER BY students_count DESC, i.first_name;