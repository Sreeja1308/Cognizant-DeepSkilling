DELIMITER //

CREATE PROCEDURE ApplyAnnualFee()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_account_id INT;
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_account_type VARCHAR(20);
    DECLARE v_fee DECIMAL(15,2);
    DECLARE v_account_count INT DEFAULT 0;
    DECLARE v_total_fees DECIMAL(15,2) DEFAULT 0;
    
    DECLARE cur CURSOR FOR 
        SELECT AccountID, Balance, AccountType
        FROM Accounts;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    DROP TEMPORARY TABLE IF EXISTS temp_fee_results;
    CREATE TEMPORARY TABLE temp_fee_results (
        ResultID INT AUTO_INCREMENT PRIMARY KEY,
        ResultLine VARCHAR(500)
    );
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_account_id, v_balance, v_account_type;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        SET v_account_count = v_account_count + 1;
        
        IF v_account_type = 'Savings' THEN
            SET v_fee = 25.00;
        ELSEIF v_account_type = 'Checking' THEN
            SET v_fee = 50.00;
        ELSE
            SET v_fee = 30.00;
        END IF;
   
        IF v_balance >= v_fee THEN
            UPDATE Accounts 
            SET Balance = Balance - v_fee,
                LastModified = NOW()
            WHERE AccountID = v_account_id;
           
            INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
            VALUES (FLOOR(RAND()*1000000), v_account_id, NOW(), v_fee, 'Annual-Fee');
            
            SET v_total_fees = v_total_fees + v_fee;
            
            INSERT INTO temp_fee_results (ResultLine) VALUES (
                CONCAT('Account ', v_account_id, ' (', v_account_type, 
                       '): Applied annual fee of $', v_fee,
                       ' (New Balance: $', ROUND(v_balance - v_fee, 2), ')')
            );
        ELSE
            INSERT INTO temp_fee_results (ResultLine) VALUES (
                CONCAT('Account ', v_account_id, ' (', v_account_type, 
                       '): INSUFFICIENT BALANCE - $', ROUND(v_balance, 2), 
                       ' < Fee $', v_fee, ' (Fee not applied)')
            );
        END IF;
    END LOOP;
    
    CLOSE cur;

    INSERT INTO temp_fee_results (ResultLine) VALUES (
        CONCAT('========== ANNUAL FEE SUMMARY ==========')
    );
    INSERT INTO temp_fee_results (ResultLine) VALUES (
        CONCAT('Accounts Processed: ', v_account_count)
    );
    INSERT INTO temp_fee_results (ResultLine) VALUES (
        CONCAT('Total Fees Collected: $', ROUND(v_total_fees, 2))
    );
    INSERT INTO temp_fee_results (ResultLine) VALUES (
        CONCAT('Date: ', DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'))
    );
    

    SELECT * FROM temp_fee_results;
   
    DROP TEMPORARY TABLE IF EXISTS temp_fee_results;
END //

DELIMITER ;


CALL ApplyAnnualFee();

SELECT AccountID, AccountType, Balance FROM Accounts;