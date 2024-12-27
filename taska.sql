-- Create Database
CREATE DATABASE todolist;

-- Use the Database
USE todolist;

-- Create Table for Tasks
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    due_date DATE,
    is_completed BOOLEAN DEFAULT FALSE
);
