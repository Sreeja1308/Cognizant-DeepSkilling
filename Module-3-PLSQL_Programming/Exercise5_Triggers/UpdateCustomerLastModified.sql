DELIMITER //

CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = NOW();
END //

DELIMITER ;


SELECT * FROM Customers WHERE CustomerID = 1;

UPDATE Customers 
SET Balance = 2500.00, Name = 'John Doe Updated' 
WHERE CustomerID = 1;

SELECT CustomerID, Name, Balance, LastModified FROM Customers WHERE CustomerID = 1;