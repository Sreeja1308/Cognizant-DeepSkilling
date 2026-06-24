-- Insert sample books
INSERT INTO books (title, author, isbn, publication_year, price, description, created_at, updated_at) VALUES
('Spring Framework Guide', 'John Doe', '978-0-13-468599-1', 2023, 49.99, 'Comprehensive guide to Spring Framework', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Clean Code', 'Robert C. Martin', '978-0-13-235088-4', 2008, 44.99, 'A handbook of agile software craftsmanship', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Design Patterns', 'Erich Gamma', '978-0-201-63361-0', 1994, 54.99, 'Elements of Reusable Object-Oriented Software', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Effective Java', 'Joshua Bloch', '978-0-13-468599-1', 2018, 49.99, 'Best practices for Java development', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Spring Boot in Action', 'Craig Walls', '978-1-61729-254-5', 2016, 39.99, 'Building microservices with Spring Boot', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);