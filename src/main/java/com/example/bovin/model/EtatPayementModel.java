package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "etat_payement")
public class EtatPayementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String libelle;

    public EtatPayementModel() {
    }

    public EtatPayementModel(String libelle) {
        this.libelle = libelle;
    }

    public EtatPayementModel(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "EtatPayementModel{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}