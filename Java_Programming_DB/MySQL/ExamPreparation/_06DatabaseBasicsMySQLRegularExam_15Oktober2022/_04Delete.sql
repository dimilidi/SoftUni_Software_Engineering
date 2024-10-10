/*
04.	Delete
Delete all waiters, who don't have any orders.
*/

USE restaurant_db;

DELETE
FROM waiters
WHERE id NOT IN (SELECT orders.waiter_id
                 FROM orders);