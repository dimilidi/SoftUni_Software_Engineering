SELECT town_id, name
FROM soft_uni.towns
WHERE name NOT REGEXP '^[MKBE]'
ORDER BY name;

/*SELECT town_id, name
FROM soft_uni.towns
WHERE name NOT LIKE ('R%') AND name NOT LIKE ('B%') AND name NOT LIKE ('D%')
ORDER BY name;*/