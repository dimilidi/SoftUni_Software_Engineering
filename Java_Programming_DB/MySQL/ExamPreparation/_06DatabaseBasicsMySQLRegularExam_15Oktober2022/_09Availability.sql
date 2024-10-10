/*
09.	Availability
There are a lot of people waiting to have dinner in the restaurant.
You can help the waiters by checking the available tables in restaurant_db.
Write a query that returns
the table_id, capacity, count_clients  and availability of all tables from the 1st floor.
Count_clients is the number of people from all orders that are sitting on that table.
Availability is based on how many people are sitting and the capacity of the table.
If the capacity is greater than count_clients then it should be "Free seats",
if the capacity is equal to the count_clients it should be "Full",
and if the capacity is lower than the count_clients it should be "Extra seats".
Order the results descending by table_id.
Required Columns
•	table_id
•	capacity
•	count_clients (is the number of people from all orders that are sitting on that table)
•	availability (based on how many people are sitting and the capacity of the table)
*/


USE restaurant_db;

SELECT t.id                AS table_id,
       t.capacity,
       COUNT(oc.client_id) AS count_clients,
       (CASE
            WHEN capacity > COUNT(oc.client_id) THEN 'Free seats'
            WHEN capacity = COUNT(oc.client_id) THEN 'Full'
            WHEN capacity < COUNT(oc.client_id) THEN 'Extra seats'
           END
           )               AS availability
FROM tables t
         JOIN orders o ON o.table_id = t.id
         JOIN orders_clients oc ON oc.order_id = o.id
WHERE floor = 1
GROUP BY t.id, t.capacity
ORDER BY t.id DESC;

