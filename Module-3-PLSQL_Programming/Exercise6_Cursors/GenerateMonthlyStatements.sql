DELIMITER //

CREATE PROCEDURE GenerateMonthlyStatements()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_customer_name VARCHAR(100);
    DECLARE v_account_id INT;
    DECLARE v_account_type VARCHAR(20);
    DECLARE v_transaction_date DATETIME;
    DECLARE v_amount DECIMAL(15,2);
    DECLARE v_transaction_type VARCHAR(10);
    DECLARE v_total_deposits DECIMAL(15,2) DEFAULT 0;
    DECLARE v_total_withdrawals DECIMAL(15,2) DEFAULT 0;
    DECLARE v_transaction_count INT DEFAULT 0;
    
    DECLARE cur CURSOR FOR 
        SELECT c.CustomerID, c.Name, a.AccountID, a.AccountType,
               t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        LEFT JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE MONTH(t.TransactionDate) = MONTH(CURDATE())
          AND YEAR(t.TransactionDate) = YEAR(CURDATE())
        ORDER BY c.CustomerID, a.AccountID, t.TransactionDate;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    DROP TEMPORARY TABLE IF EXISTS temp_statement;
    CREATE TEMPORARY TABLE temp_statement (
        LineNumber INT AUTO_INCREMENT PRIMARY KEY,
        StatementLine VARCHAR(500)
    );
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_customer_id, v_customer_name, v_account_id, 
                        v_account_type, v_transaction_date, v_amount, v_transaction_type;
        
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        SET v_transaction_count = v_transaction_count + 1;
        
        INSERT INTO temp_statement (StatementLine) VALUES (
            CONCAT(
                DATE_FORMAT(v_transaction_date, '%Y-%m-%d %H:%i'),
                ' | Customer: ', v_customer_name, 
                ' (ID: ', v_customer_id, ')',
                ' | Account: ', v_account_id, 
                ' (', v_account_type, ')',
                ' | ', v_transaction_type,
                ' | $', ROUND(v_amount, 2)
            )
        );
        
        IF v_transaction_type IN ('Deposit', 'Interest', 'Transfer-In', 'Initial-Deposit') THEN
            SET v_total_deposits = v_total_deposits + v_amount;
        ELSEIF v_transaction_type IN ('Withdrawal', 'Transfer-Out', 'Annual-Fee', 'Account-Closure') THEN
            SET v_total_withdrawals = v_total_withdrawals + v_amount;
        END IF;
    END LOOP;
    
    CLOSE cur;
    
    -- Insert summary
    INSERT INTO temp_statement (StatementLine) VALUES (
        CONCAT(
            'MONTHLY STATEMENT SUMMARY'
        )
    );
    INSERT INTO temp_statement (StatementLine) VALUES (
        CONCAT(
            'Total Deposits: $', ROUND(v_total_deposits, 2),
            ' | Total Withdrawals: $', ROUND(v_total_withdrawals, 2),
            ' | Net Change: $', ROUND(v_total_deposits - v_total_withdrawals, 2)
        )
    );
    INSERT INTO temp_statement (StatementLine) VALUES (
        CONCAT(
            'Total Transactions: ', v_transaction_count,
            ' | Statement Period: ', DATE_FORMAT(CURDATE(), '%M %Y')
        )
    );
    
    SELECT * FROM temp_statement;
    
    DROP TEMPORARY TABLE IF EXISTS temp_statement;
END //

DELIMITER ;

CALL GenerateMonthlyStatements();


SELECT c.Name, a.AccountID, t.TransactionDate, t.Amount, t.TransactionType
FROM Customers c
JOIN Accounts a ON c.CustomerID = a.CustomerID
JOIN Transactions t ON a.AccountID = t.AccountID
WHERE MONTH(t.TransactionDate) = MONTH(CURDATE())
  AND YEAR(t.TransactionDate) = YEAR(CURDATE())
ORDER BY c.CustomerID, t.TransactionDate;