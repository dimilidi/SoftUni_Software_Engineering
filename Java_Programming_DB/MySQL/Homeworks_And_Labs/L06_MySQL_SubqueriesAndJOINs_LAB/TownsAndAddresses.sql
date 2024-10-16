SELECT
    t.town_id,
    t.name AS town_name,
    a.address_text
FROM addresses a
         JOIN towns t ON t.town_id = a.town_id
WHERE t.name IN('San Francisco', 'Sofia', 'Carnation')
ORDER BY a.town_id, a.address_id;