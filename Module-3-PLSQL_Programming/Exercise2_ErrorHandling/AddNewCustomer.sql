DELIMITER //

CREATE PROCEDURE AddNewCustomer(
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


CALL AddNewCustomer(6, 'Thomas Wilson', '1980-12-01', 5000.00);
CALL AddNewCustomer(7, 'Sarah Davis', '1975-06-15', 15000.00);
CALL AddNewCustomer(1, 'Duplicate Customer', '1990-01-01', 1000.00);


SELECT * FROM Customers WHERE CustomerID >= 6;