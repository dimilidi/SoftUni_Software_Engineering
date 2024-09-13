DELIMITER $$

CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
    RETURNS TINYINT
    DETERMINISTIC
BEGIN
RETURN
    (SELECT word REGEXP CONCAT('^[', set_of_letters, ']+$'));
END$$
DELIMITER ;

SELECT ufn_is_word_comprised('bobr', 'rob'); // 1
SELECT ufn_is_word_comprised('oistmiahf', 'Sofia'); // 1
SELECT ufn_is_word_comprised('oistmiahf', 'halves'); // 0
SELECT ufn_is_word_comprised('pppp', 'Guy'); // 0