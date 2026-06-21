DELIMITER //

CREATE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount DECIMAL(15,2),
    p_interest_rate DECIMAL(5,2),
    p_duration_years INT
)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_monthly_rate DECIMAL(15,10);
    DECLARE v_num_payments INT;
    DECLARE v_installment DECIMAL(15,2);
    
    SET v_monthly_rate = (p_interest_rate / 100) / 12;
    
    SET v_num_payments = p_duration_years * 12;
    
    IF v_monthly_rate = 0 THEN
        SET v_installment = p_loan_amount / v_num_payments;
    ELSE
        SET v_installment = p_loan_amount * 
                           (v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments)) / 
                           (POWER(1 + v_monthly_rate, v_num_payments) - 1);
    END IF;
    
    RETURN ROUND(v_installment, 2);
END //

DELIMITER ;

SELECT 
    LoanID,
    CustomerID,
    LoanAmount,
    InterestRate,
    TIMESTAMPDIFF(YEAR, StartDate, EndDate) AS DurationYears,
    CalculateMonthlyInstallment(LoanAmount, InterestRate, TIMESTAMPDIFF(YEAR, StartDate, EndDate)) AS MonthlyInstallment,
    CalculateMonthlyInstallment(LoanAmount, InterestRate, TIMESTAMPDIFF(YEAR, StartDate, EndDate)) * 
        (TIMESTAMPDIFF(YEAR, StartDate, EndDate) * 12) AS TotalPayment,
    CalculateMonthlyInstallment(LoanAmount, InterestRate, TIMESTAMPDIFF(YEAR, StartDate, EndDate)) * 
        (TIMESTAMPDIFF(YEAR, StartDate, EndDate) * 12) - LoanAmount AS TotalInterest
FROM Loans;