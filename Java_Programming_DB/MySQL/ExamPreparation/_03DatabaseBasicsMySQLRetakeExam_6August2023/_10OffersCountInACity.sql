DELIMITER $$

CREATE FUNCTION udf_offers_from_city_name (cityName VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
RETURN (
    SELECT COUNT(*)
    FROM property_offers po
             JOIN properties p ON p.id = po.property_id
             JOIN cities c ON c.id = p.city_id
    WHERE c.name = cityName);
END$$
DELIMITER ;

SELECT udf_offers_from_city_name('Vienna')  AS offers_count;
SELECT udf_offers_from_city_name('Amsterdam') AS offers_count;