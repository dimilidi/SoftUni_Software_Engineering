/*
02.	Insert
You will have to insert records of data into the athletes table.
For all athletes, which come from a country whose name starts with the letter "A",
insert data in the athletes table with the following values:
•	first_name – set it to the first name of the athlete but uppercase.
•	last_name – set it to the athlete's last name followed by a white space and
then "comes from" followed by one more white space and the name of the athlete's country.
e.g.(last_name+" comes from "+(country)name)
•	age – set it to the sum of the age of the athlete and his country_id.
•	country_id – keep the same value.
*/

USE summer_olympics;

INSERT INTO athletes(first_name, last_name, age, country_id)
SELECT
    UPPER(a.first_name),
    CONCAT(a.last_name, ' comes from ', c.name),
    a.age + a.country_id,
    a.country_id
FROM athletes a
         JOIN countries c ON a.country_id = c.id
WHERE c.name LIKE 'A%';