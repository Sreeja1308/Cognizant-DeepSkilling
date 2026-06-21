DELIMITER //

CREATE FUNCTION CalculateAge(p_dob DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE v_age INT;
    
    SET v_age = TIMESTAMPDIFF(YEAR, p_dob, CURDATE());
    
    IF DATE_FORMAT(CURDATE(), '%m-%d') < DATE_FORMAT(p_dob, '%m-%d') THEN
        SET v_age = v_age - 1;
    END IF;
    
    RETURN v_age;
END //

DELIMITER ;

SELECT 
    CustomerID, 
    Name, 
    DOB, 
    CalculateAge(DOB) AS Age,
    CASE 
        WHEN CalculateAge(DOB) >= 60 THEN 'Senior'
        WHEN CalculateAge(DOB) >= 40 THEN 'Mid-Career'
        ELSE 'Young Professional'
    END AS AgeGroup
FROM Customers
ORDER BY Age DESC;