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
CREATE OR REPLACE VIEW v_bovin_date_dernier_recencement_poid as
SELECT 
    rpb.id_bovin, Max(rpb.date_recensement) date_dernier_recensement_poid
FROM  recensement_poid_bovin rpb 
GROUP BY rpb.id_bovin;

-- vue pour recuperer le deriner recemcement de poid d un bovin
CREATE OR REPLACE VIEW v_bovin_dernier_recencement_poid as
SELECT 
    rpb.*
FROM recensement_poid_bovin rpb 
    JOIN v_bovin_date_dernier_recencement_poid vbddrp 
        ON rpb.id_bovin = vbddrp.id_bovin AND rpb.date_recensement = vbddrp.date_dernier_recensement_poid;

-- vue pour recuperer les info de bovin et son dernier poid
CREATE OR REPLACE VIEW v_bovin_poid_actuel as
SELECT 
    b.*,
    vdrp.poid poid_actuel
FROM bovin b
    JOIN v_bovin_dernier_recencement_poid vdrp
        on b.id = vdrp.id_bovin;


-- vue bovin  avec les information de mois et du poids actuel 
CREATE OR REPLACE VIEW v_bovin_mois_actuel_poid_actuel as
SELECT 
    b.*,
    vdrp.poid poid_actuel,
    (EXTRACT(YEAR FROM AGE(CURRENT_DATE, date_arrive)) * 12 + 
    EXTRACT(MONTH FROM AGE(CURRENT_DATE, date_arrive)))  + mois_init AS mois_actuel
FROM bovin b   
JOIN v_bovin_dernier_recencement_poid vdrp
        on b.id = vdrp.id_bovin;

-- Module : Fournisseurs

CREATE TABLE type_fournisseur (
    id_type SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL, -- Un code court pour ton code Java (ex: 'BOVIN', 'ALIMENT')
    libelle VARCHAR(50) NOT NULL       -- Hoe inona ilay avarotrany amintsika (ex: 'Bovins / Bétail', 'Aliments / Fourrage')
);

CREATE TABLE produit (
    id_produit SERIAL PRIMARY KEY,
    nom_produit VARCHAR(100) NOT NULL
);

CREATE TABLE fournisseur (
    id_fournisseur SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    id_type_fournisseur INT NOT NULL, -- Clé étrangère vers le type
    telephone VARCHAR(20),
    e_mail VARCHAR(100),
    adresse TEXT,
    nom_interlocuteur VARCHAR(100),   -- Le nom de ton contact physique
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Déclaration de la Foreign Key
    CONSTRAINT fk_fournisseur_type 
        FOREIGN KEY (id_type_fournisseur) 
        REFERENCES type_fournisseur(id_type)
);

CREATE TABLE produit_fournisseur (
    id_fournisseur INT,
    id_produit INT,
    prix_unitaire DECIMAL(10, 2) NOT NULL,
    unite_mesure VARCHAR(20), 
    minimum_commande INT DEFAULT 0,
    delai_livraison_jours INT, 
    
    PRIMARY KEY (id_fournisseur, id_produit),
    
    CONSTRAINT fk_prod_fourn_fournisseur
        FOREIGN KEY (id_fournisseur) 
        REFERENCES fournisseur(id_fournisseur),
        
    CONSTRAINT fk_prod_fourn_produit
        FOREIGN KEY (id_produit) 
        REFERENCES produit(id_produit)
);
-- Insertion des types de base pour ton projet d'engraissement
INSERT INTO type_fournisseur (code, libelle) VALUES
('BOVIN', 'Bovins (Naisseurs / Éleveurs)'),
('ALIMENT', 'Aliments & Fourrage'),
('SANITAIRE', 'Soins Vétérinaires & Médicaments'),
('MATERIEL', 'Équipements & Matériel d''élevage');

