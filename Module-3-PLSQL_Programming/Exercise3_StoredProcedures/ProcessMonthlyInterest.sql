DELIMITER //

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_account_id INT;
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_interest_rate DECIMAL(5,2) DEFAULT 1.00;
    DECLARE v_interest_amount DECIMAL(15,2);
    DECLARE v_account_count INT DEFAULT 0;
    
  
    DECLARE cur CURSOR FOR 
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings';
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_account_id, v_balance;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
      
        SET v_interest_amount = v_balance * (v_interest_rate / 100);
        SET v_account_count = v_account_count + 1;
        
      
        UPDATE Accounts 
        SET Balance = Balance + v_interest_amount,
            LastModified = NOW()
        WHERE AccountID = v_account_id;
        
       
        INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
        VALUES (FLOOR(RAND()*1000000), v_account_id, NOW(), v_interest_amount, 'Interest');
        
        SELECT CONCAT('Account ', v_account_id, 
                     ': Added interest of $', ROUND(v_interest_amount, 2),
                     ' (New Balance: $', ROUND(v_balance + v_interest_amount, 2), ')') AS ProcessMessage;
    END LOOP;
    
    CLOSE cur;
    
    IF v_account_count = 0 THEN
        SELECT 'No savings accounts found to process interest.' AS Message;
    ELSE
        SELECT CONCAT('Processed interest for ', v_account_count, ' savings accounts.') AS Summary;
    END IF;
END //

DELIMITER ;


CALL ProcessMonthlyInterest();


SELECT AccountID, AccountType, Balance FROM Accounts;