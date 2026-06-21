DELIMITER //

CREATE PROCEDURE UpdateSalary(
    IN p_employee_id INT,
    IN p_percentage DECIMAL(5,2)
)
BEGIN
    DECLARE v_employee_exists INT DEFAULT 0;
    DECLARE v_current_salary DECIMAL(15,2);
    DECLARE v_new_salary DECIMAL(15,2);
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error updating salary. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
  
    SELECT COUNT(*) INTO v_employee_exists FROM Employees WHERE EmployeeID = p_employee_id;
    
    IF v_employee_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Employee ', p_employee_id, ' not found.') AS ErrorMessage;
    ELSE
       
        SELECT Salary INTO v_current_salary FROM Employees WHERE EmployeeID = p_employee_id;
        SET v_new_salary = v_current_salary * (1 + p_percentage/100);
        
       
        UPDATE Employees 
        SET Salary = v_new_salary
        WHERE EmployeeID = p_employee_id;
        
        COMMIT;
        SELECT CONCAT('Salary updated for employee ', p_employee_id, 
                     ': $', v_current_salary, ' -> $', v_new_salary, 
                     ' (', p_percentage, '% increase)') AS SuccessMessage;
    END IF;
END //

DELIMITER ;


CALL UpdateSalary(1, 10.00);
CALL UpdateSalary(999, 10.00);

SELECT * FROM Employees WHERE EmployeeID = 1;