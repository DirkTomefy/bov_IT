package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_payement")
public class TypePayementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String libelle;

    public TypePayementModel() {
    }

    public TypePayementModel(String libelle) {
        this.libelle = libelle;
    }

    public TypePayementModel(Integer id, String libelle) {
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
        return "TypePayementModel{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}