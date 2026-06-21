DELIMITER //

CREATE PROCEDURE UpdateVIPStatus()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_balance DECIMAL(15,2);
    
    
    DECLARE cur CURSOR FOR 
        SELECT CustomerID, Balance
        FROM Customers;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_customer_id, v_balance;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        
        IF v_balance > 10000 THEN
            UPDATE Customers 
            SET IsVIP = TRUE 
            WHERE CustomerID = v_customer_id;
            
            SELECT CONCAT('Customer ', v_customer_id, 
                         ' promoted to VIP status (Balance: $', v_balance, ')') AS Message;
        ELSE
            SELECT CONCAT('Customer ', v_customer_id, 
                         ' not eligible for VIP (Balance: $', v_balance, ')') AS Message;
        END IF;
    END LOOP;
    
    CLOSE cur;
END //

DELIMITER ;


CALL UpdateVIPStatus();


SELECT CustomerID, Name, Balance, IsVIP FROM Customers;