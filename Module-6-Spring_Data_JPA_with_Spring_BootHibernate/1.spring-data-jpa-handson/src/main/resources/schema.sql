-- Create schema
CREATE SCHEMA IF NOT EXISTS ormlearn;

-- Use the schema
USE ormlearn;

-- Drop existing table if exists
DROP TABLE IF EXISTS country;

-- Create country table
CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

-- Insert sample data
INSERT INTO country (co_code, co_name) VALUES ('IN', 'India');
INSERT INTO country (co_code, co_name) VALUES ('US', 'United States');
INSERT INTO country (co_code, co_name) VALUES ('UK', 'United Kingdom');
INSERT INTO country (co_code, co_name) VALUES ('CA', 'Canada');
INSERT INTO country (co_code, co_name) VALUES ('AU', 'Australia');
INSERT INTO country (co_code, co_name) VALUES ('JP', 'Japan');
INSERT INTO country (co_code, co_name) VALUES ('DE', 'Germany');
INSERT INTO country (co_code, co_name) VALUES ('FR', 'France');
INSERT INTO country (co_code, co_name) VALUES ('IT', 'Italy');
INSERT INTO country (co_code, co_name) VALUES ('ES', 'Spain');
INSERT INTO country (co_code, co_name) VALUES ('BR', 'Brazil');
INSERT INTO country (co_code, co_name) VALUES ('CN', 'China');
INSERT INTO country (co_code, co_name) VALUES ('RU', 'Russia');
INSERT INTO country (co_code, co_name) VALUES ('ZA', 'South Africa');
INSERT INTO country (co_code, co_name) VALUES ('EG', 'Egypt');
INSERT INTO country (co_code, co_name) VALUES ('MX', 'Mexico');
INSERT INTO country (co_code, co_name) VALUES ('KR', 'South Korea');
INSERT INTO country (co_code, co_name) VALUES ('NL', 'Netherlands');
INSERT INTO country (co_code, co_name) VALUES ('SE', 'Sweden');
INSERT INTO country (co_code, co_name) VALUES ('CH', 'Switzerland');
INSERT INTO country (co_code, co_name) VALUES ('NO', 'Norway');
INSERT INTO country (co_code, co_name) VALUES ('NZ', 'New Zealand');
INSERT INTO country (co_code, co_name) VALUES ('SG', 'Singapore');
INSERT INTO country (co_code, co_name) VALUES ('MY', 'Malaysia');
INSERT INTO country (co_code, co_name) VALUES ('PH', 'Philippines');