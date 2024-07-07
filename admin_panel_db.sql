CREATE DATABASE simple_admin_db;

USE simple_admin_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL
);