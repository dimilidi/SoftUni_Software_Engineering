DELIMITER $$

CREATE FUNCTION udf_average_lesson_price_by_city (name VARCHAR(40))
    RETURNS DECIMAL(19, 2)
    DETERMINISTIC

BEGIN
DECLARE avg_price_for_city DECIMAL(19, 2);
SET avg_price_for_city =
	(SELECT ROUND(AVG(ds.average_lesson_price), 2)
	FROM driving_schools ds
		JOIN cities c ON c.id = ds.city_id
	WHERE c.name = name
	GROUP BY c.name);
RETURN avg_price_for_city;

END$$
DELIMITER ;


SELECT c.name, udf_average_lesson_price_by_city ('London') as average_lesson_price
FROM cities c
WHERE c.name = 'London';