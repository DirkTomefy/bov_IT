CREATE DATABASE bovin_db;
\c bovin_db;
CREATE TABLE race (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);


CREATE TABLE lot (
    id SERIAL PRIMARY KEY,
    id_race INTEGER NOT NULL,
    nombre INTEGER NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_lot_race
        FOREIGN KEY (id_race)
        REFERENCES race(id)
);

CREATE TABLE bovin (
    id SERIAL PRIMARY KEY,
    poids_init DOUBLE PRECISION NOT NULL,
    poids_actuel DOUBLE PRECISION NOT NULL,
    id_lot INTEGER NOT NULL,
    mois_init INTEGER NOT NULL,
    mois_actuel INTEGER NOT NULL,
    date_arrive DATE,

    CONSTRAINT fk_bovin_lot
        FOREIGN KEY (id_lot)
        REFERENCES lot(id)
);
