DELIMITER $$

CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(19,4), yearly_interest_rate DOUBLE, years INT)
    RETURNS DECIMAL(19, 4)
    DETERMINISTIC
BEGIN
	DECLARE future_value DECIMAL(19, 4);
    SET future_value = sum * POWER(1 + yearly_interest_rate, years);
RETURN future_value;
END$$;

DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.5, 5);