DELIMITER //

CREATE FUNCTION HasSufficientBalance(
    p_account_id INT,
    p_amount DECIMAL(15,2)
)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_account_exists INT DEFAULT 0;
    DECLARE v_result BOOLEAN DEFAULT FALSE;
    
    SELECT COUNT(*) INTO v_account_exists FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_account_exists = 0 THEN
        RETURN FALSE;
    END IF;
    
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_balance >= p_amount THEN
        SET v_result = TRUE;
    END IF;
    
    RETURN v_result;
END //

DELIMITER ;


SELECT 
    AccountID,
    CustomerID,
    Balance,
    HasSufficientBalance(AccountID, 500.00) AS 'Has $500',
    HasSufficientBalance(AccountID, 2000.00) AS 'Has $2000',
    CASE 
        WHEN HasSufficientBalance(AccountID, 500.00) THEN 'Can transact $500'
        ELSE 'Cannot transact $500'
    END AS StatusFor500
FROM Accounts
ORDER BY AccountID;