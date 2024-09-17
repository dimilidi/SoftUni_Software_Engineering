SELECT SUBSTRING(address, 1, 6) AS agent_name, CHAR_LENGTH(address) * 5430 AS price
FROM properties
         LEFT JOIN property_offers po ON po.property_id = properties.id
WHERE po.agent_id IS NULL
ORDER BY agent_name DESC, price DESC;