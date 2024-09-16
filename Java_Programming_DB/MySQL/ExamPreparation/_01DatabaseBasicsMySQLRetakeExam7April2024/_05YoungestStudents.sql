SELECT CONCAT_WS(' ', first_name, last_name) AS full_name, age
FROM students
WHERE age = (SELECT MIN(age) FROM students)
  AND first_name LIKE('%a%')
ORDER BY id;