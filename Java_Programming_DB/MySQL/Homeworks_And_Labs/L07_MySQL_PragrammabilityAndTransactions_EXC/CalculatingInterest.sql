DELIMITER $$

CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT
    a.id AS account_id,
    ah.first_name,
    ah.last_name,
    a.balance AS current_balanace,
    ufn_calculate_future_value(a.balance, interest_rate, 5)  AS balance_in_5_years
FROM account_holders ah
         JOIN accounts a ON a.account_holder_id = ah.id
WHERE a.id = account_id;
END$$

DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);