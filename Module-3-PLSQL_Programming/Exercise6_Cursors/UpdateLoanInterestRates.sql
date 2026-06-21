DELIMITER //

CREATE PROCEDURE UpdateLoanInterestRates()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_loan_id INT;
    DECLARE v_current_rate DECIMAL(5,2);
    DECLARE v_loan_amount DECIMAL(15,2);
    DECLARE v_new_rate DECIMAL(5,2);
    DECLARE v_loan_count INT DEFAULT 0;
    
    DECLARE cur CURSOR FOR 
        SELECT LoanID, LoanAmount, InterestRate
        FROM Loans;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    DROP TEMPORARY TABLE IF EXISTS temp_rate_updates;
    CREATE TEMPORARY TABLE temp_rate_updates (
        UpdateID INT AUTO_INCREMENT PRIMARY KEY,
        UpdateLine VARCHAR(500)
    );
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_loan_id, v_loan_amount, v_current_rate;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        SET v_loan_count = v_loan_count + 1;
        
        
        CASE
            WHEN v_loan_amount < 5000 THEN
                SET v_new_rate = v_current_rate + 0.50;
            WHEN v_loan_amount BETWEEN 5000 AND 15000 THEN
                SET v_new_rate = v_current_rate - 0.25;
            ELSE
                SET v_new_rate = v_current_rate - 0.50;
        END CASE;
        
        IF v_new_rate < 3.00 THEN
            SET v_new_rate = 3.00;
        END IF;
        IF v_new_rate > 8.00 THEN
            SET v_new_rate = 8.00;
        END IF;
        
        UPDATE Loans 
        SET InterestRate = v_new_rate
        WHERE LoanID = v_loan_id;
        
        INSERT INTO temp_rate_updates (UpdateLine) VALUES (
            CONCAT('Loan ', v_loan_id, 
                   ': $', ROUND(v_loan_amount, 2),
                   ' | ', ROUND(v_current_rate, 2), '% -> ', 
                   ROUND(v_new_rate, 2), '%',
                   ' (Change: ', ROUND(v_new_rate - v_current_rate, 2), '%)')
        );
    END LOOP;
    
    CLOSE cur;
    
    INSERT INTO temp_rate_updates (UpdateLine) VALUES (
        CONCAT('INTEREST RATE UPDATE SUMMARY')
    );
    INSERT INTO temp_rate_updates (UpdateLine) VALUES (
        CONCAT('Loans Processed: ', v_loan_count)
    );
    INSERT INTO temp_rate_updates (UpdateLine) VALUES (
        CONCAT('Date: ', DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'))
    );
    INSERT INTO temp_rate_updates (UpdateLine) VALUES (
        CONCAT('Policy: Rate adjustments based on loan amount')
    );
    
    SELECT * FROM temp_rate_updates;
    
    DROP TEMPORARY TABLE IF EXISTS temp_rate_updates;
END //

DELIMITER ;

CALL UpdateLoanInterestRates();

SELECT LoanID, LoanAmount, InterestRate FROM Loans;