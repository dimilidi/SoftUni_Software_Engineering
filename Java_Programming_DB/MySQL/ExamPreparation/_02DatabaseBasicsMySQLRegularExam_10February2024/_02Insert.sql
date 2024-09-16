INSERT INTO preserves(name, latitude, longitude, area, type, established_on)
SELECT
    CONCAT_WS(' ',name, 'is in South Hemisphere') AS name,
    latitude,
    longitude,
    area * id,
    LOWER(type),
    established_on
FROM preserves
WHERE latitude < 0;