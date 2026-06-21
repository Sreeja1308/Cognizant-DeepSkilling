DELIMITER //

CREATE PROCEDURE UpdateEmployeeBonus(
    IN p_department VARCHAR(50),
    IN p_bonus_percentage DECIMAL(5,2)
)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_employee_id INT;
    DECLARE v_name VARCHAR(100);
    DECLARE v_salary DECIMAL(15,2);
    DECLARE v_bonus_amount DECIMAL(15,2);
    DECLARE v_employee_count INT DEFAULT 0;
    
    DECLARE cur CURSOR FOR 
        SELECT EmployeeID, Name, Salary
        FROM Employees
        WHERE Department = p_department;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    read_loop: LOOP
        FETCH cur INTO v_employee_id, v_name, v_salary;
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        
        SET v_bonus_amount = v_salary * (p_bonus_percentage / 100);
        SET v_employee_count = v_employee_count + 1;
        
       
        UPDATE Employees 
        SET Salary = Salary + v_bonus_amount
        WHERE EmployeeID = v_employee_id;
        
        SELECT CONCAT('Employee ', v_name, ' (ID: ', v_employee_id, 
                     '): Added bonus of $', ROUND(v_bonus_amount, 2),
                     ' (New Salary: $', ROUND(v_salary + v_bonus_amount, 2), ')') AS ProcessMessage;
    END LOOP;
    
    CLOSE cur;
    
    IF v_employee_count = 0 THEN
        SELECT CONCAT('No employees found in department: ', p_department) AS Message;
    ELSE
        SELECT CONCAT('Processed bonuses for ', v_employee_count, ' employees in ', 
                     p_department, ' department.') AS Summary;
    END IF;
END //

DELIMITER ;


CALL UpdateEmployeeBonus('IT', 10.00);
CALL UpdateEmployeeBonus('HR', 15.00);


SELECT * FROM Employees;