package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produit")
public class ProduitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nomProduit;

    public ProduitModel() {
    }

    public ProduitModel(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public ProduitModel(Integer id, String nomProduit) {
        this.id = id;
        this.nomProduit = nomProduit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    @Override
    public String toString() {
        return "ProduitModel{" +
                "id=" + id +
                ", nomProduit='" + nomProduit + '\'' +
                '}';
    }
}