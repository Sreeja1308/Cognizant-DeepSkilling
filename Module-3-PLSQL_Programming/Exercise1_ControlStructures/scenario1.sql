DELIMITER //

CREATE PROCEDURE ApplySeniorDiscount()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_dob DATE;
    DECLARE v_age INT;
    DECLARE v_interest_rate DECIMAL(5,2);
    DECLARE v_loan_id INT;
    
    
    DECLARE cur CURSOR FOR 
        SELECT DISTINCT c.CustomerID, c.DOB, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_customer_id, v_dob, v_loan_id, v_interest_rate;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        
        SET v_age = TIMESTAMPDIFF(YEAR, v_dob, CURDATE());
        
        
        IF v_age > 60 THEN
            UPDATE Loans 
            SET InterestRate = InterestRate - 1.00 
            WHERE LoanID = v_loan_id;
            
            SELECT CONCAT('Applied 1% discount to loan ', v_loan_id, 
                         ' for customer ', v_customer_id, 
                         ' (Age: ', v_age, ' years)') AS Message;
        END IF;
    END LOOP;
    
    CLOSE cur;
END //

DELIMITER ;


CALL ApplySeniorDiscount();


SELECT l.LoanID, c.Name, c.DOB, 
       TIMESTAMPDIFF(YEAR, c.DOB, CURDATE()) AS Age,
       l.InterestRate
FROM Loans l
JOIN Customers c ON l.CustomerID = c.CustomerID;