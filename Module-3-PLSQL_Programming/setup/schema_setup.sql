
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Loans;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS AuditLog;


CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(15,2),
    LastModified DATETIME,
    IsVIP BOOLEAN DEFAULT FALSE
);


CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR(20),
    Balance DECIMAL(15,2),
    LastModified DATETIME,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY,
    AccountID INT,
    TransactionDate DATETIME,
    Amount DECIMAL(15,2),
    TransactionType VARCHAR(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);


CREATE TABLE Loans (
    LoanID INT PRIMARY KEY,
    CustomerID INT,
    LoanAmount DECIMAL(15,2),
    InterestRate DECIMAL(5,2),
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary DECIMAL(15,2),
    Department VARCHAR(50),
    HireDate DATE
);


CREATE TABLE AuditLog (
    LogID INT AUTO_INCREMENT PRIMARY KEY,
    TableName VARCHAR(50),
    Operation VARCHAR(20),
    RecordID INT,
    OldValue TEXT,
    NewValue TEXT,
    ChangedBy VARCHAR(50),
    ChangeDate DATETIME
);


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES 
(1, 'John Doe', '1985-05-15', 1000.00, NOW(), FALSE),
(2, 'Jane Smith', '1990-07-20', 1500.00, NOW(), FALSE),
(3, 'Robert Johnson', '1960-03-10', 12000.00, NOW(), FALSE),
(4, 'Mary Williams', '1975-11-25', 8500.00, NOW(), FALSE),
(5, 'James Brown', '1955-08-30', 500.00, NOW(), FALSE);


INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES 
(1, 1, 'Savings', 1000.00, NOW()),
(2, 2, 'Checking', 1500.00, NOW()),
(3, 3, 'Savings', 12000.00, NOW()),
(4, 4, 'Checking', 8500.00, NOW()),
(5, 5, 'Savings', 500.00, NOW());


INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES 
(1, 1, NOW(), 200.00, 'Deposit'),
(2, 2, NOW(), 300.00, 'Withdrawal'),
(3, 3, NOW(), 500.00, 'Deposit'),
(4, 4, NOW(), 1000.00, 'Withdrawal'),
(5, 5, NOW(), 100.00, 'Deposit');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES 
(1, 1, 5000.00, 5.00, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 60 MONTH)),
(2, 3, 10000.00, 4.50, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 48 MONTH)),
(3, 4, 20000.00, 6.00, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 120 MONTH));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES 
(1, 'Alice Johnson', 'Manager', 70000.00, 'HR', '2015-06-15'),
(2, 'Bob Brown', 'Developer', 60000.00, 'IT', '2017-03-20'),
(3, 'Carol White', 'Analyst', 65000.00, 'Finance', '2018-09-10');


SELECT 'Database setup completed successfully!' AS Status;
SELECT * FROM Customers;
SELECT * FROM Accounts;
SELECT * FROM Loans;
SELECT * FROM Employees;