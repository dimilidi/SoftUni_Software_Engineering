/*SELECT town_id, name
FROM soft_uni.towns
WHERE name LIKE ('M%') OR name LIKE ('K%') OR name LIKE ('B%') OR name LIKE ('E%')
ORDER BY name;*/

SELECT town_id, name
FROM soft_uni.towns
WHERE name REGEXP '^[MKBE]'
ORDER BY name;