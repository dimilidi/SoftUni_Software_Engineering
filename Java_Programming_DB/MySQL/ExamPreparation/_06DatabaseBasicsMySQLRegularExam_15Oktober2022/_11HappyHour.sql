/*
11.	Happy hour
Create a stored procedure udp_happy_hour which accepts the following parameters:
•	type (VARCHAR(50))
Extracts data about the products from the given type
and reduces the prices by 20% of all products
which have prices higher or equal to 10.00 and are from the given type.
*/

USE restaurant_db;

DELIMITER $$

CREATE PROCEDURE udp_happy_hour(IN product_type VARCHAR(50))
BEGIN
    UPDATE products p
    SET p.price = p.price * 0.8
    WHERE p.type = product_type
      AND p.price >= 10;
END$$

DELIMITER ;


CALL udp_happy_hour('Cognac');
