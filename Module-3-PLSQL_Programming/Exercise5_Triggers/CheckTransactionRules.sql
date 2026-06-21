DELIMITER //

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_account_exists INT DEFAULT 0;

    SELECT COUNT(*) INTO v_account_exists FROM Accounts WHERE AccountID = NEW.AccountID;
    
    IF v_account_exists = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = CONCAT('Error: Account ', NEW.AccountID, ' does not exist.');
    END IF;
    
  
    IF NEW.TransactionType IN ('Withdrawal', 'Transfer-Out', 'Account-Closure') THEN
       
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = NEW.AccountID;
        
        IF v_balance < NEW.Amount THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = CONCAT('Insufficient funds for ', NEW.TransactionType, 
                                      '. Available: $', v_balance, ', Requested: $', NEW.Amount);
        END IF;
        
      
        IF NEW.Amount <= 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = CONCAT(NEW.TransactionType, ' amount must be positive');
        END IF;
    END IF;
    

    IF NEW.TransactionType IN ('Deposit', 'Interest', 'Transfer-In', 'Initial-Deposit') THEN
    
        IF NEW.Amount <= 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = CONCAT(NEW.TransactionType, ' amount must be positive');
        END IF;
        
       
        IF NEW.Amount > 1000000 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = CONCAT(NEW.TransactionType, ' exceeds maximum limit of $1,000,000');
        END IF;
    END IF;
    
   
    IF NEW.TransactionType NOT IN ('Deposit', 'Withdrawal', 'Transfer-In', 'Transfer-Out', 
                                   'Interest', 'Annual-Fee', 'Account-Closure', 'Initial-Deposit') THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = CONCAT('Invalid transaction type: ', NEW.TransactionType);
    END IF;
END //

DELIMITER ;


INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (102, 1, NOW(), 100.00, 'Deposit');


INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (103, 5, NOW(), 1000.00, 'Withdrawal');


INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (104, 1, NOW(), -50.00, 'Deposit');


INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (105, 999, NOW(), 100.00, 'Deposit');


SELECT * FROM Transactions WHERE TransactionID >= 100;