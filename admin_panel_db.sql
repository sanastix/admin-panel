CREATE DATABASE admin_panel_db;

USE admin_panel_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL
);