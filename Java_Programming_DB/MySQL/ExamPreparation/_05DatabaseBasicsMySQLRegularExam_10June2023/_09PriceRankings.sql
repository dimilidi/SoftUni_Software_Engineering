/*
09.	Price rankings
Make it easier for students when they are searching for a new university.
From the database extract the university_name, city_name, address, price_rank and tuition_fee.
If the tuition fee is less than 800 (exclusive) the user must see "cheap",
equal or above 800 and less than 1200 it should display "normal",
equal or above 1200 and less than 2500 it should display "high"
and equal or above that it should display "expensive".
Order the results ascending by tuition_fee.
Required Columns
•	university_name
•	city_name
•	address
•	price_rank (less than 800 – "cheap", equal or above 800 and less than 1200 – "normal", equal or above 1200 and less than 2500 – "high", equal or above 2500 – "expensive)
•	tuition_fee
*/

USE universities_db;

SELECT u.name AS university_name,
       c.name AS city_name,
       u.address,
       (
           CASE
               WHEN u.tuition_fee < 800 THEN 'cheap'
               WHEN u.tuition_fee < 1200 THEN 'normal'
               WHEN u.tuition_fee < 2500 THEN 'high'
               WHEN u.tuition_fee >= 2500 THEN 'expensive'
           END
           )
              AS price_rank,
       u.tuition_fee
FROM universities u
         JOIN cities c ON c.id = u.city_id
ORDER BY u.tuition_fee;