DELIMITER $$

CREATE PROCEDURE usp_get_towns_starting_with(nameBegin VARCHAR(250))
BEGIN
SELECT name
FROM towns
WHERE name LIKE CONCAT(nameBegin,'%')
ORDER BY name;
END$$

DELIMITER ;

CALL usp_get_towns_starting_with('B');