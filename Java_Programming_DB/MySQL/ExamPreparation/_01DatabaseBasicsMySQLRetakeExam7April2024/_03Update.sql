UPDATE driving_schools ds
    JOIN cities c ON c.id = ds.city_id
    SET ds.average_lesson_price = ds.average_lesson_price + 30
WHERE c.name = 'London' AND ds.night_time_driving = 1;