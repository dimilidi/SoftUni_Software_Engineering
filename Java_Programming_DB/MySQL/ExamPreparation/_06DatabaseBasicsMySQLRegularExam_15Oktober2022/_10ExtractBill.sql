/*
10.	Extract bill
Create a user defined function with the name udf_client_bill(full_name VARCHAR(50))
that receives a customer's full name and returns the total price of products he ordered;
Required Columns
•	first_name (client)
•	last_name (client)
•	bill (udf_client_bill) (should be DECIMAL(19,2))
*/

USE restaurant_db;

DELIMITER $$

CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
    RETURNS DECIMAL(19, 2)
    DETERMINISTIC
BEGIN
    RETURN (SELECT SUM(p.price)
            FROM products p
                     JOIN orders_products op ON p.id = op.product_id
                     JOIN orders o ON o.id = op.order_id
                     JOIN orders_clients oc ON oc.order_id = o.id
                     JOIN clients c ON c.id = oc.client_id
            WHERE c.first_name LIKE LEFT(full_name, CHAR_LENGTH(c.first_name))
              AND c.last_name LIKE RIGHT(full_name, CHAR_LENGTH(c.last_name))
            GROUP BY c.id);
END $$

DELIMITER ;

SELECT c.first_name,c.last_name, udf_client_bill('Silvio Blyth') as 'bill' FROM clients c
WHERE c.first_name = 'Silvio' AND c.last_name= 'Blyth';
