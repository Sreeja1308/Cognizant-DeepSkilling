DELIMITER //

CREATE PROCEDURE SafeTransferFunds(
    IN p_from_account_id INT,
    IN p_to_account_id INT,
    IN p_amount DECIMAL(15,2)
)
BEGIN
    DECLARE v_from_balance DECIMAL(15,2);
    DECLARE v_to_balance DECIMAL(15,2);
    DECLARE v_from_exists INT DEFAULT 0;
    DECLARE v_to_exists INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Transaction failed. Error occurred. All changes rolled back.' AS ErrorMessage;
    END;
    
   
    START TRANSACTION;
    
    
    SELECT COUNT(*) INTO v_from_exists FROM Accounts WHERE AccountID = p_from_account_id;
    SELECT COUNT(*) INTO v_to_exists FROM Accounts WHERE AccountID = p_to_account_id;
    
    IF v_from_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Source account ', p_from_account_id, ' does not exist.') AS ErrorMessage;
    ELSEIF v_to_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Destination account ', p_to_account_id, ' does not exist.') AS ErrorMessage;
    ELSE
      
        SELECT Balance INTO v_from_balance FROM Accounts WHERE AccountID = p_from_account_id;
        
    
        IF v_from_balance < p_amount THEN
            ROLLBACK;
            SELECT CONCAT('Insufficient funds. Available: $', v_from_balance, 
                         ', Required: $', p_amount) AS ErrorMessage;
        ELSE
            
            UPDATE Accounts 
            SET Balance = Balance - p_amount, 
                LastModified = NOW() 
            WHERE AccountID = p_from_account_id;
            
            UPDATE Accounts 
            SET Balance = Balance + p_amount, 
                LastModified = NOW() 
            WHERE AccountID = p_to_account_id;
            
           
            INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
            VALUES (FLOOR(RAND()*1000000), p_from_account_id, NOW(), p_amount, 'Transfer-Out');
            
            INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
            VALUES (FLOOR(RAND()*1000000), p_to_account_id, NOW(), p_amount, 'Transfer-In');
            
            COMMIT;
            SELECT CONCAT('Successfully transferred $', p_amount, ' from account ', 
                          p_from_account_id, ' to account ', p_to_account_id) AS SuccessMessage;
        END IF;
    END IF;
END //

DELIMITER ;


CALL SafeTransferFunds(1, 2, 100.00);
CALL SafeTransferFunds(1, 2, 2000.00);
CALL SafeTransferFunds(999, 1, 100.00);


SELECT AccountID, Balance FROM Accounts WHERE AccountID IN (1, 2);