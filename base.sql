CREATE DATABASE bovin_db;
\c bovin_db;
CREATE TABLE race (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);


CREATE TABLE lot (
    id SERIAL PRIMARY KEY,
    idRace INTEGER NOT NULL,
    nombre INTEGER NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_lot_race
        FOREIGN KEY (idRace)
        REFERENCES race(id)
);