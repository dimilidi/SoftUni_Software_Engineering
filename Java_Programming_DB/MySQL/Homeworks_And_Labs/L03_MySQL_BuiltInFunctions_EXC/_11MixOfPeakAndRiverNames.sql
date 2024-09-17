SELECT peak_name, river_name, CONCAT(LOWER(peak_name), SUBSTRING(LOWER(river_name), 2)) as 'mix'
FROM geography.peaks, geography.rivers
WHERE RIGHT(peak_name, 1) =  LEFT(river_name, 1)
ORDER BY mix;