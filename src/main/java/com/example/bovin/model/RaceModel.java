package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "race")
public class RaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String libelle;

    public RaceModel() {
    }

    public RaceModel(String libelle) {
        this.libelle = libelle;
    }

    public RaceModel(Integer id, String libelle) {
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
        return "RaceModel{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}