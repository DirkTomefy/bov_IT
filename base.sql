CREATE DATABASE bovin_db;
\c bovin_db;
CREATE TABLE race (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);