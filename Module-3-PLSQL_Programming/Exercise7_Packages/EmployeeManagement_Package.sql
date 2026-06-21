DELIMITER //

CREATE PROCEDURE EmployeeManagement_HireEmployee(
    IN p_employee_id INT,
    IN p_name VARCHAR(100),
    IN p_position VARCHAR(50),
    IN p_salary DECIMAL(15,2),
    IN p_department VARCHAR(50)
)
BEGIN
    DECLARE v_employee_exists INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error hiring employee. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO v_employee_exists FROM Employees WHERE EmployeeID = p_employee_id;
    
    IF v_employee_exists > 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Employee ', p_employee_id, ' already exists.') AS ErrorMessage;
    ELSE
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, CURDATE());
        
        COMMIT;
        SELECT CONCAT('Employee ', p_employee_id, ' (', p_name, ') hired successfully.') AS SuccessMessage;
    END IF;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE EmployeeManagement_UpdateEmployee(
    IN p_employee_id INT,
    IN p_name VARCHAR(100),
    IN p_position VARCHAR(50),
    IN p_salary DECIMAL(15,2),
    IN p_department VARCHAR(50)
)
BEGIN
    DECLARE v_employee_exists INT DEFAULT 0;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error updating employee. Transaction rolled back.' AS ErrorMessage;
    END;
    
    START TRANSACTION;
    
    SELECT COUNT(*) INTO v_employee_exists FROM Employees WHERE EmployeeID = p_employee_id;
    
    IF v_employee_exists = 0 THEN
        ROLLBACK;
        SELECT CONCAT('Error: Employee ', p_employee_id, ' not found.') AS ErrorMessage;
    ELSE
        UPDATE Employees 
        SET Name = p_name,
            Position = p_position,
            Salary = p_salary,
            Department = p_department
        WHERE EmployeeID = p_employee_id;
        
        COMMIT;
        SELECT CONCAT('Employee ', p_employee_id, ' (', p_name, ') updated successfully.') AS SuccessMessage;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION EmployeeManagement_CalculateAnnualSalary(p_employee_id INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_salary DECIMAL(15,2);
    DECLARE v_employee_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_employee_exists FROM Employees WHERE EmployeeID = p_employee_id;
    
    IF v_employee_exists = 0 THEN
        RETURN -1.00;
    END IF;
    
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_employee_id;
    
    RETURN v_salary * 12;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION EmployeeManagement_GetDepartment(p_employee_id INT)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE v_department VARCHAR(50);
    DECLARE v_employee_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO v_employee_exists FROM Employees WHERE EmployeeID = p_employee_id;
    
    IF v_employee_exists = 0 THEN
        RETURN 'Unknown';
    END IF;
    
    SELECT Department INTO v_department FROM Employees WHERE EmployeeID = p_employee_id;
    
    RETURN IFNULL(v_department, 'Unknown');
END //

DELIMITER ;


CALL EmployeeManagement_HireEmployee(10, 'John Smith', 'Developer', 65000.00, 'IT');
CALL EmployeeManagement_HireEmployee(11, 'Jane Davis', 'Designer', 60000.00, 'Design');


CALL EmployeeManagement_UpdateEmployee(10, 'John Smith Updated', 'Senior Developer', 70000.00, 'IT');


SELECT 
    EmployeeID,
    Name,
    Position,
    Salary,
    Department,
    EmployeeManagement_CalculateAnnualSalary(EmployeeID) AS AnnualSalary,
    EmployeeManagement_GetDepartment(EmployeeID) AS DepartmentFromFunction,
    CASE 
        WHEN EmployeeManagement_CalculateAnnualSalary(EmployeeID) > 800000 THEN 'High Earner'
        WHEN EmployeeManagement_CalculateAnnualSalary(EmployeeID) > 600000 THEN 'Mid Earner'
        ELSE 'Standard Earner'
    END AS SalaryCategory
FROM Employees
WHERE EmployeeID IN (10, 11);


SELECT * FROM Employees;