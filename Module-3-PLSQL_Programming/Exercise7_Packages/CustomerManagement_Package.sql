DELIMITER //

CREATE PROCEDURE CustomerManagement_AddCustomer(
    IN p_customer_id INT,
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_balance DECIMAL(15,2)
)
BEGIN
    DECLARE v_customer_exists INT DEFAULT 0;
    DECLARE v_vip_status BOOLEAN;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error adding customer. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists > 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Customer ', p_customer_id, ' already exists.') AS ErrorMessage;
    ELSE
        SET v_vip_status = CASE WHEN p_balance > 10000 THEN TRUE ELSE FALSE END;
        
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
        VALUES (p_customer_id, p_name, p_dob, p_balance, NOW(), v_vip_status);
        
        COMMIT;
        SELECT CONCAT('Customer ', p_customer_id, ' added successfully.', 
                     CASE WHEN v_vip_status THEN ' (VIP status granted)' ELSE '' END) AS SuccessMessage;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE CustomerManagement_UpdateCustomer(
    IN p_customer_id INT,
    IN p_name VARCHAR(100),
    IN p_dob DATE,
    IN p_balance DECIMAL(15,2)
)
BEGIN
    DECLARE v_customer_exists INT DEFAULT 0;
    DECLARE v_vip_status BOOLEAN;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error updating customer. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Customer ', p_customer_id, ' not found.') AS ErrorMessage;
    ELSE
        SET v_vip_status = CASE WHEN p_balance > 10000 THEN TRUE ELSE FALSE END;
        
        UPDATE Customers 
        SET Name = p_name,
            DOB = p_dob,
            Balance = p_balance,
            IsVIP = v_vip_status
        WHERE CustomerID = p_customer_id;
        
        COMMIT;
        SELECT CONCAT('Customer ', p_customer_id, ' updated successfully.', 
                     CASE WHEN v_vip_status THEN ' (VIP status granted)' ELSE '' END) AS SuccessMessage;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION CustomerManagement_GetBalance(p_customer_id INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_balance DECIMAL(15,2);
    DECLARE v_customer_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists = 0 THEN
        RETURN -1.00;
    END IF;
    
    SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_customer_id;
    
    RETURN IFNULL(v_balance, 0.00);
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION CustomerManagement_IsVIP(p_customer_id INT)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE v_is_vip BOOLEAN;
    DECLARE v_customer_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_customer_exists FROM Customers WHERE CustomerID = p_customer_id;
    
    IF v_customer_exists = 0 THEN
        RETURN FALSE;
    END IF;
    
    SELECT IsVIP INTO v_is_vip FROM Customers WHERE CustomerID = p_customer_id;
    
    RETURN IFNULL(v_is_vip, FALSE);
END //

DELIMITER ;

-- Add new customers
CALL CustomerManagement_AddCustomer(10, 'Test Customer 1', '1990-01-01', 5000.00);
CALL CustomerManagement_AddCustomer(11, 'Test Customer 2', '1980-06-15', 15000.00);

CALL CustomerManagement_UpdateCustomer(10, 'Updated Customer', '1985-05-15', 12000.00);


SELECT 
    CustomerID,
    Name,
    Balance,
    CustomerManagement_GetBalance(CustomerID) AS GetBalance,
    CustomerManagement_IsVIP(CustomerID) AS IsVIP,
    CASE 
        WHEN CustomerManagement_IsVIP(CustomerID) THEN 'VIP Member'
        ELSE 'Regular Member'
    END AS MembershipLevel
FROM Customers
WHERE CustomerID IN (10, 11);

SELECT CustomerID, Name, Balance, IsVIP, LastModified FROM Customers;