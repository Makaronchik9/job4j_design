CREATE DATABASE job4j_db;

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    brand TEXT,
    year INT,
    electric BOOLEAN
);

INSERT INTO cars (brand, year, electric)
VALUES ('Tesla', 2022, true);

UPDATE cars
SET year = 2023;

DELETE FROM cars;