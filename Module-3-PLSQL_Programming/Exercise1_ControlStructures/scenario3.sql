DELIMITER //

CREATE PROCEDURE SendLoanReminders()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_customer_id INT;
    DECLARE v_customer_name VARCHAR(100);
    DECLARE v_loan_id INT;
    DECLARE v_end_date DATE;
    DECLARE v_days_until_due INT;
    
  
    DECLARE cur CURSOR FOR 
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_loan_id, v_customer_id, v_end_date, v_customer_name;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
     
        SET v_days_until_due = DATEDIFF(v_end_date, CURDATE());
        
        
        SELECT CONCAT(
            'REMINDER: Loan ', v_loan_id, 
            ' for customer ', v_customer_name,
            ' (ID: ', v_customer_id, ')',
            ' is due in ', v_days_until_due, ' days on ', v_end_date
        ) AS ReminderMessage;
    END LOOP;
    
    CLOSE cur;
    
    
    IF ROW_COUNT() = 0 THEN
        SELECT 'No loans are due within the next 30 days.' AS Message;
    END IF;
END //

DELIMITER ;


CALL SendLoanReminders();


SELECT l.LoanID, c.Name, l.EndDate, 
       DATEDIFF(l.EndDate, CURDATE()) AS DaysUntilDue,
       CASE 
           WHEN DATEDIFF(l.EndDate, CURDATE()) <= 30 THEN 'Due Soon'
           ELSE 'Not Due'
       END AS Status
FROM Loans l
JOIN Customers c ON l.CustomerID = c.CustomerID
ORDER BY DaysUntilDue;