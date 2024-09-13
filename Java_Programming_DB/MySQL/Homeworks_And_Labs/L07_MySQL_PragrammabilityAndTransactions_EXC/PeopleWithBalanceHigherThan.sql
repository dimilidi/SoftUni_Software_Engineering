DELIMITER $$

CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance DECIMAL(12,00))
BEGIN
SELECT  ah.first_name, ah.last_name
FROM accounts a
         JOIN account_holders ah ON ah.id = a.account_holder_id
GROUP BY ah.id
HAVING SUM(a.balance) > balance
ORDER BY ah.id;
END$$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);