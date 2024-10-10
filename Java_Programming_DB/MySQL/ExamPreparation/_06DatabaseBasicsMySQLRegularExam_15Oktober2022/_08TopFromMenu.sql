/*
08.	Top from menu
There are many items on our menu list,
but the owner wants to know which one is the best sellable item from the restaurant.
Extract from the database the id (product), the name and the count of products from all orders
with this name where the count is greater or equal to 5.
Order the results descending by count and then by name in ascending.
Required Columns
•	id (product)
•	name (product)
•	count (the count of products with the same name)
*/

USE restaurant_db;

SELECT p.id, p.name, COUNT(*) AS count
FROM products p
JOIN orders_products op ON op.product_id = p.id
GROUP BY p.id, p.name
HAVING count >= 5
ORDER BY count DESC, p.name;