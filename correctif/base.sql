CREATE DATABASE bovin_db;
\c bovin_db;

-- ==========================
-- RACE
-- ==========================
CREATE TABLE race (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE caisse(
    id SERIAL PRIMARY KEY,
    prix_actuel INTEGER 
);

CREATE TABLE mouvement_caisse(
    id_fournisseur INTEGER ,
    id_caisse INTEGER NOT NULL,

    type_mouvement VARCHAR(10) NOT NULL
        CHECK (type_mouvement IN ('ENTREE','SORTIE')),
    prix_total INTEGER NOT NULL,
    date_mouvement TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT fk_mouvement_caisse
        FOREIGN KEY (id_caisse)
        REFERENCES matiere(id)
);

-- ==========================
-- TYPE MATIERE
-- ==========================
CREATE TABLE type_matiere (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    libelle VARCHAR(50) NOT NULL
);

-- ==========================
-- MATIERE
-- ==========================
CREATE TABLE matiere (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    code_type_matiere VARCHAR(20) NOT NULL,

    CONSTRAINT fk_matiere_type
        FOREIGN KEY (code_type_matiere)
        REFERENCES type_matiere(code)
);

-- ==========================
-- FOURNISSEUR
-- ==========================
CREATE TABLE fournisseur_info (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    telephone VARCHAR(20),
    e_mail VARCHAR(100),
    adresse TEXT,
    nom_interlocuteur VARCHAR(100),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================
-- FOURNISSEUR / TYPE MATIERE
-- ==========================
CREATE TABLE fournisseur_matiere (
    id_fournisseur INTEGER NOT NULL,
    code_type_matiere VARCHAR(20) NOT NULL,

    PRIMARY KEY (id_fournisseur, code_type_matiere),

    CONSTRAINT fk_fournisseur_matiere_fournisseur
        FOREIGN KEY (id_fournisseur)
        REFERENCES fournisseur_info(id),

    CONSTRAINT fk_fournisseur_matiere_type
        FOREIGN KEY (code_type_matiere)
        REFERENCES type_matiere(code)
);

-- ==========================
-- MOUVEMENT MATIERE
-- ==========================
CREATE TABLE mouvement_matiere (
    id SERIAL PRIMARY KEY,

    id_fournisseur INTEGER ,
    id_matiere INTEGER NOT NULL,

    type_mouvement VARCHAR(10) NOT NULL
        CHECK (type_mouvement IN ('ENTREE','SORTIE')),

    prix_unitaire DECIMAL(10,2) NOT NULL,
    unite_mesure VARCHAR(20),
    qte DOUBLE PRECISION NOT NULL,
    date_mouvement TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT fk_mouvement_matiere
        FOREIGN KEY (id_matiere)
        REFERENCES matiere(id)
);

-- ==========================
-- TYPE PAIEMENT
-- ==========================
CREATE TABLE type_payement (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- ==========================
-- ETAT PAIEMENT
-- ==========================
CREATE TABLE etat_payement (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL -- PAYEE / DETTE
);

-- ==========================
-- ACHAT
-- ==========================
CREATE TABLE achat (
    id SERIAL PRIMARY KEY,

    id_fournisseur INTEGER NOT NULL,
    date_achat TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    id_type_payement INTEGER NOT NULL,
    id_etat_payement INTEGER NOT NULL,

    prix_total DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_achat_fournisseur
        FOREIGN KEY (id_fournisseur)
        REFERENCES fournisseur_info(id),

    CONSTRAINT fk_achat_type_payement
        FOREIGN KEY (id_type_payement)
        REFERENCES type_payement(id),

    CONSTRAINT fk_achat_etat_payement
        FOREIGN KEY (id_etat_payement)
        REFERENCES etat_payement(id)
);

-- ==========================
-- DETAIL ACHAT
-- ==========================
CREATE TABLE achat_details (
    id SERIAL PRIMARY KEY,

    id_achat INTEGER NOT NULL,
    id_matiere INTEGER NOT NULL,

    quantite DOUBLE PRECISION NOT NULL,
    prix_unitaire_facture DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_details_achat
        FOREIGN KEY (id_achat)
        REFERENCES achat(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_details_matiere
        FOREIGN KEY (id_matiere)
        REFERENCES matiere(id)
);

-- ==========================
-- LOT
-- ==========================
CREATE TABLE lot (
    id SERIAL PRIMARY KEY,

    id_race INTEGER NOT NULL,
    id_fournisseur INTEGER,

    nombre INTEGER NOT NULL,

    CONSTRAINT fk_lot_race
        FOREIGN KEY (id_race)
        REFERENCES race(id),

    CONSTRAINT fk_lot_fournisseur
        FOREIGN KEY (id_fournisseur)
        REFERENCES fournisseur_info(id)
);

-- ==========================
-- BOVIN
-- ==========================
CREATE TABLE bovin (
    id SERIAL PRIMARY KEY,

    poids_init DOUBLE PRECISION NOT NULL,
    mois_init INTEGER NOT NULL,

    id_lot INTEGER,
    date_arrive DATE,

    CONSTRAINT fk_bovin_lot
        FOREIGN KEY (id_lot)
        REFERENCES lot(id)
);

-- ==========================
-- RECENSEMENT POIDS BOVIN
-- ==========================
CREATE TABLE recensement_poid_bovin (
    id SERIAL PRIMARY KEY,

    id_bovin INTEGER NOT NULL,
    poid DOUBLE PRECISION NOT NULL,
    date_recensement DATE,

    CONSTRAINT fk_recensement_poid_bovin
        FOREIGN KEY (id_bovin)
        REFERENCES bovin(id)
);

-- =====================================
-- CLIENT
-- =====================================
CREATE TABLE client (
    id SERIAL PRIMARY KEY,

    nom VARCHAR(150) NOT NULL,
    telephone VARCHAR(20),
    e_mail VARCHAR(100),
    adresse TEXT,

    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================
-- STATUT COMMANDE
-- =====================================
CREATE TABLE statut_commande (
    id SERIAL PRIMARY KEY,

    code VARCHAR(30) UNIQUE NOT NULL,
    libelle VARCHAR(100) NOT NULL
);

-- Exemples :
-- EN_ATTENTE
-- VALIDEE
-- ANNULEE
-- LIVREE

-- =====================================
-- COMMANDE
-- =====================================
CREATE TABLE commande (
    id SERIAL PRIMARY KEY,

    id_client INTEGER NOT NULL,

    date_commande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    montant_total DOUBLE PRECISION DEFAULT 0,

    CONSTRAINT fk_commande_client
        FOREIGN KEY (id_client)
        REFERENCES client(id)
);

-- =====================================
-- HISTORIQUE STATUT COMMANDE
-- =====================================
CREATE TABLE commande_statut (
    id SERIAL PRIMARY KEY,

    id_commande INTEGER NOT NULL,
    id_statut INTEGER NOT NULL,

    date_statut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    commentaire TEXT,

    CONSTRAINT fk_commande_statut_commande
        FOREIGN KEY (id_commande)
        REFERENCES commande(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_commande_statut_statut
        FOREIGN KEY (id_statut)
        REFERENCES statut_commande(id)
);

-- =====================================
-- DETAIL COMMANDE
-- =====================================
CREATE TABLE commande_detail (
    id SERIAL PRIMARY KEY,

    id_commande INTEGER NOT NULL,
    id_bovin INTEGER NOT NULL,

    prix_prevu DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_commande_detail_commande
        FOREIGN KEY (id_commande)
        REFERENCES commande(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_commande_detail_bovin
        FOREIGN KEY (id_bovin)
        REFERENCES bovin(id)
);

-- =====================================
-- TYPE PAIEMENT
-- =====================================
CREATE TABLE type_payement (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- Exemples :
-- ESPECE
-- MVOLA
-- ORANGE_MONEY
-- VIREMENT
-- CHEQUE

-- =====================================
-- VENTE
-- =====================================
CREATE TABLE vente (
    id SERIAL PRIMARY KEY,

    id_commande INTEGER NOT NULL,

    date_vente TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    montant_total DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_vente_commande
        FOREIGN KEY (id_commande)
        REFERENCES commande(id)
);

-- =====================================
-- DETAIL VENTE
-- =====================================
CREATE TABLE vente_detail (
    id SERIAL PRIMARY KEY,

    id_vente INTEGER NOT NULL,

    id_bovin INTEGER NOT NULL,

    id_type_payement INTEGER NOT NULL,

    prix_unitaire DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_vente_detail_vente
        FOREIGN KEY (id_vente)
        REFERENCES vente(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_vente_detail_bovin
        FOREIGN KEY (id_bovin)
        REFERENCES bovin(id),

    CONSTRAINT fk_vente_detail_type_payement
        FOREIGN KEY (id_type_payement)
        REFERENCES type_payement(id)
);