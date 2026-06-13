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
    id_lot INTEGER ,
    mois_init INTEGER NOT NULL,
    date_arrive DATE,

    CONSTRAINT fk_bovin_lot
        FOREIGN KEY (id_lot)
        REFERENCES lot(id)
);

CREATE TABLE recensement_poid_bovin (
    id SERIAL PRIMARY KEY,
    id_bovin INTEGER NOT NULL,
    poid DOUBLE PRECISION NOT NULL,
    date_recensement DATE,

     CONSTRAINT fk_recensement_poid_bovin
        FOREIGN KEY (id_bovin)
        REFERENCES bovin(id)
);

-- vue pour recuperer la date du dernier recnesement de poid des bovin
CREATE VIEW v_bovin_date_dernier_recencement_poid as
SELECT 
    rpb.id_bovin, Max(rpb.date_recensement) date_dernier_recensement_poid
FROM  recensement_poid_bovin rpb 
GROUP BY rpb.id_bovin;

-- vue pour recuperer le deriner recemcement de poid d un bovin
CREATE VIEW v_bovin_dernier_recencement_poid as
SELECT 
    rpb.*
FROM recensement_poid_bovin rpb 
    JOIN v_bovin_date_dernier_recencement_poid vbddrp 
        ON rpb.id_bovin = vbddrp.id_bovin AND rpb.date_recensement = vbddrp.date_dernier_recensement_poid;

-- vue pour recuperer les info de bovin et son dernier poid
CREATE VIEW v_bovin_poid_actuel as
SELECT 
    b.*,
    vdrp.poid poid_actuel
FROM bovin b
    JOIN v_bovin_dernier_recencement_poid vdrp
        on b.id = vdrp.id_bovin;


-- vue bovin  avec les information de mois et du poids actuel 
CREATE VIEW v_bovin_mois_actuel_poid_actuel as
SELECT 
    b.*,
    vdrp.poid poid_actuel,
    (EXTRACT(YEAR FROM AGE(CURRENT_DATE, date_arrive)) * 12 + 
    EXTRACT(MONTH FROM AGE(CURRENT_DATE, date_arrive)))  + mois_init AS mois_actuel
FROM bovin b   
JOIN v_bovin_dernier_recencement_poid vdrp
        on b.id = vdrp.id_bovin;