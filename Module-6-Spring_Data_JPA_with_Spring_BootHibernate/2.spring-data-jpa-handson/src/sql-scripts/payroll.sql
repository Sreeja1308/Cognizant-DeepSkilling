-- Create database if not exists
CREATE DATABASE IF NOT EXISTS ormlearn;
USE ormlearn;

-- Drop existing tables
DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS skill;

-- Create department table
CREATE TABLE IF NOT EXISTS department (
    dp_id INT NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(50),
    PRIMARY KEY (dp_id)
);

-- Create employee table
CREATE TABLE IF NOT EXISTS employee (
    em_id INT NOT NULL AUTO_INCREMENT,
    em_name VARCHAR(50),
    em_salary DECIMAL(10,2),
    em_permanent BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id INT,
    PRIMARY KEY (em_id),
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

-- Create skill table
CREATE TABLE IF NOT EXISTS skill (
    sk_id INT NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(50),
    PRIMARY KEY (sk_id)
);

-- Create employee_skill junction table
CREATE TABLE IF NOT EXISTS employee_skill (
    es_em_id INT NOT NULL,
    es_sk_id INT NOT NULL,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);

-- Insert sample data
INSERT INTO department (dp_name) VALUES 
('IT'),
('HR'),
('Finance'),
('Marketing');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES
('Alice Johnson', 80000, true, '1985-05-15', 1),
('Bob Smith', 65000, true, '1990-08-20', 2),
('Carol White', 70000, false, '1988-12-10', 1),
('David Brown', 55000, true, '1992-03-25', 3),
('Eve Davis', 90000, true, '1983-07-05', 1);

INSERT INTO skill (sk_name) VALUES
('Java'),
('Python'),
('SQL'),
('JavaScript'),
('Spring Boot');

INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES
(1, 1), (1, 3), (1, 5),
(2, 2), (2, 4),
(3, 1), (3, 3),
(4, 2), (4, 4),
(5, 1), (5, 5);