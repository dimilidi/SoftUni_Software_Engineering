/*
03.	Update
Remove the word "weight" from any discipline containing it.
*/

USE summer_olympics;

UPDATE disciplines
SET name = REPLACE(name, 'weight', '')
WHERE name LIKE '%weight%';
