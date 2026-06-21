DELIMITER //

CREATE PROCEDURE AccountOperations_OpenAccount(
    IN p_account_id INT,
    IN p_customer_id INT,
    IN p_account_type VARCHAR(20),
    IN p_initial_balance DECIMAL(15,2)
)
BEGIN
    DECLARE v_customer_exists INT DEFAULT 0;
    DECLARE v_account_exists INT DEFAULT 0;
    DECLARE v_customer_balance DECIMAL(15,2);
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error opening account. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    SELECT COUNT(*) INTO v_account_exists FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_customer_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Customer ', p_customer_id, ' not found.') AS ErrorMessage;
    ELSEIF v_account_exists > 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Account ', p_account_id, ' already exists.') AS ErrorMessage;
    ELSEIF p_initial_balance < 0 THEN
        ROLLBACK;
        SELECT 'Error: Initial balance cannot be negative.' AS ErrorMessage;
    ELSE
        -- Create the account
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_initial_balance, NOW());
        
        IF p_initial_balance > 0 THEN
            UPDATE Customers 
            SET Balance = Balance + p_initial_balance
            WHERE CustomerID = p_customer_id;
            
            INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
            VALUES (FLOOR(RAND()*1000000), p_account_id, NOW(), p_initial_balance, 'Initial-Deposit');
        END IF;
        
        COMMIT;
        SELECT CONCAT('Account ', p_account_id, ' opened successfully for customer ', p_customer_id,
                     ' (Type: ', p_account_type, ', Initial Balance: $', ROUND(p_initial_balance, 2), ')') AS SuccessMessage;
    END IF;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE AccountOperations_CloseAccount(
    IN p_account_id INT
)
BEGIN
    DECLARE v_customer_id INT;
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_account_exists INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error closing account. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*), CustomerID, Balance INTO v_account_exists, v_customer_id, v_balance 
    FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_account_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Account ', p_account_id, ' not found.') AS ErrorMessage;
    ELSE
  
        IF v_balance > 0 THEN
            UPDATE Customers 
            SET Balance = Balance + v_balance
            WHERE CustomerID = v_customer_id;
            
            INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
            VALUES (FLOOR(RAND()*1000000), p_account_id, NOW(), v_balance, 'Account-Closure');
        END IF;
        
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        
        COMMIT;
        SELECT CONCAT('Account ', p_account_id, ' closed successfully. ',
                     'Remaining balance of $', ROUND(v_balance, 2), ' transferred to customer.') AS SuccessMessage;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION AccountOperations_GetTotalBalance(p_customer_id INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_total DECIMAL(15,2);
    DECLARE v_customer_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists = 0 THEN
        RETURN -1.00;
    END IF;
    
    SELECT SUM(Balance) INTO v_total 
    FROM Accounts 
    WHERE CustomerID = p_customer_id;
    
    RETURN IFNULL(v_total, 0.00);
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION AccountOperations_GetAccountCount(
    p_customer_id INT,
    p_account_type VARCHAR(20)
)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE v_count INT;
    DECLARE v_customer_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists = 0 THEN
        RETURN -1;
    END IF;
    
    SELECT COUNT(*) INTO v_count 
    FROM Accounts 
    WHERE CustomerID = p_customer_id AND AccountType = p_account_type;
    
    RETURN IFNULL(v_count, 0);
END //

DELIMITER ;

CALL AccountOperations_OpenAccount(100, 1, 'Savings', 500.00);
CALL AccountOperations_OpenAccount(101, 1, 'Checking', 300.00);
CALL AccountOperations_OpenAccount(102, 2, 'Savings', 1000.00);

SELECT 
    CustomerID,
    Name,
    Balance AS CustomerBalance,
    AccountOperations_GetTotalBalance(CustomerID) AS TotalAccountBalance,
    AccountOperations_GetAccountCount(CustomerID, 'Savings') AS SavingsCount,
    AccountOperations_GetAccountCount(CustomerID, 'Checking') AS CheckingCount
FROM Customers
WHERE CustomerID IN (1, 2);

SELECT * FROM Accounts WHERE CustomerID IN (1, 2);

CALL AccountOperations_CloseAccount(100);

SELECT * FROM Accounts WHERE CustomerID IN (1, 2);
SELECT CustomerID, Name, Balance FROM Customers WHERE CustomerID IN (1, 2);