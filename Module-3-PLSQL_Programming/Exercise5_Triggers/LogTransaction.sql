DELIMITER //

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TableName, Operation, RecordID, OldValue, NewValue, ChangedBy, ChangeDate)
    VALUES (
        'Transactions',
        'INSERT',
        NEW.TransactionID,
        NULL,
        CONCAT('AccountID: ', NEW.AccountID, 
               ', Amount: $', NEW.Amount, 
               ', Type: ', NEW.TransactionType,
               ', Date: ', NEW.TransactionDate),
        USER(),
        NOW()
    );
END //

DELIMITER ;

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (100, 1, NOW(), 150.00, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (101, 2, NOW(), 75.00, 'Withdrawal');

SELECT * FROM AuditLog WHERE TableName = 'Transactions' ORDER BY LogID DESC LIMIT 10;