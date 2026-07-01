-- Sample departments
INSERT INTO departments (name, description) VALUES 
('IT', 'Information Technology Department'),
('HR', 'Human Resources Department'),
('Finance', 'Finance and Accounting Department'),
('Marketing', 'Marketing and Sales Department'),
('Operations', 'Operations and Logistics Department');

-- Sample employees
INSERT INTO employees (name, email, phone_number, position, salary, department_id) VALUES 
('John Doe', 'john.doe@company.com', '123-456-7890', 'Software Engineer', 75000.00, 1),
('Jane Smith', 'jane.smith@company.com', '123-456-7891', 'HR Manager', 65000.00, 2),
('Bob Johnson', 'bob.johnson@company.com', '123-456-7892', 'Accountant', 55000.00, 3),
('Alice Brown', 'alice.brown@company.com', '123-456-7893', 'Marketing Director', 85000.00, 4),
('Charlie Wilson', 'charlie.wilson@company.com', '123-456-7894', 'Operations Manager', 70000.00, 5),
('Eve Davis', 'eve.davis@company.com', '123-456-7895', 'Software Architect', 95000.00, 1),
('Frank Miller', 'frank.miller@company.com', '123-456-7896', 'HR Specialist', 45000.00, 2),
('Grace Lee', 'grace.lee@company.com', '123-456-7897', 'Financial Analyst', 60000.00, 3);