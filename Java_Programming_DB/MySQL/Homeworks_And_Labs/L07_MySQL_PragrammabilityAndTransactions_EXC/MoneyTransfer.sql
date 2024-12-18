DELIMITER $$

CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
START TRANSACTION;
    IF  (
        (SELECT COUNT(*) FROM accounts WHERE id = from_account_id) <> 1 OR
        (SELECT COUNT(*) FROM accounts WHERE id = to_account_id) <> 1 OR
        (SELECT balance FROM accounts WHERE id = from_account_id) < amount OR
        amount <= 0
    ) THEN
      ROLLBACK;
    ELSE
        CALL usp_deposit_money(to_account_id, amount);
        CALL usp_withdraw_money(from_account_id, amount);
        COMMIT;
END IF;
END$$

DELIMITER ;
