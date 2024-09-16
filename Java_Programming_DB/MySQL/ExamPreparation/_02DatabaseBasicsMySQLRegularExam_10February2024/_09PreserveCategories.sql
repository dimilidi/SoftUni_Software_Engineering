SELECT
    p.id,
    p.name,
    (CASE
         WHEN p.area < 100 THEN 'very small'
         WHEN p.area BETWEEN 101 AND 1000 THEN 'small'
         WHEN p.area BETWEEN 1001 AND 10000 THEN 'medium'
         WHEN p.area BETWEEN 10001 AND 50000 THEN 'large'
         WHEN p.area > 50000 THEN 'very large'
        END) AS category
FROM preserves p
ORDER BY p.area DESC;