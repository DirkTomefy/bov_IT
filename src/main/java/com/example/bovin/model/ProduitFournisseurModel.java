package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produit_fournisseur")
public class ProduitFournisseurModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur", nullable = false)
    private FournisseurModel fournisseur;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private ProduitModel produit;

    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaireProduit;

    @Column(name = "unite_mesure", length = 20)
    private String uniteMesure;

    public ProduitFournisseurModel() {
    }

    public ProduitFournisseurModel(FournisseurModel fournisseur, ProduitModel produit, Double prixUnitaireProduit, String uniteMesure) {
        this.fournisseur = fournisseur;
        this.produit = produit;
        this.prixUnitaireProduit = prixUnitaireProduit;
        this.uniteMesure = uniteMesure;
    }

    public ProduitFournisseurModel(Integer id, FournisseurModel fournisseur, ProduitModel produit, Double prixUnitaireProduit, String uniteMesure) {
        this.id = id;
        this.fournisseur = fournisseur;
        this.produit = produit;
        this.prixUnitaireProduit = prixUnitaireProduit;
        this.uniteMesure = uniteMesure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FournisseurModel getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(FournisseurModel fournisseur) {
        this.fournisseur = fournisseur;
    }

    public ProduitModel getProduit() {
        return produit;
    }

    public void setProduit(ProduitModel produit) {
        this.produit = produit;
    }

    public Double getPrixUnitaireProduit() {
        return prixUnitaireProduit;
    }

    public void setPrixUnitaireProduit(Double prixUnitaireProduit) {
        this.prixUnitaireProduit = prixUnitaireProduit;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    @Override
    public String toString() {
        return "ProduitFournisseurModel{" +
                "id=" + id +
                ", fournisseur=" + (fournisseur != null ? fournisseur.getId() : null) +
                ", produit=" + (produit != null ? produit.getNomProduit() : null) +
                ", prixUnitaireProduit=" + prixUnitaireProduit +
                ", uniteMesure='" + uniteMesure + '\'' +
                '}';
    }
}