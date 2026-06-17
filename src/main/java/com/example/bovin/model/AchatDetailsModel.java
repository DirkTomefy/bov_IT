package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "achat_details")
public class AchatDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_achat", nullable = false)
    private AchatModel achat;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private ProduitModel produit;

    @Column(nullable = false)
    private Double quantite;

    @Column(name = "prix_unitaire_facture", nullable = false)
    private Double prixUnitaireFacture;

    public AchatDetailsModel() {
    }

    public AchatDetailsModel(AchatModel achat, ProduitModel produit, Double quantite, Double prixUnitaireFacture) {
        this.achat = achat;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaireFacture = prixUnitaireFacture;
    }

    public AchatDetailsModel(Integer id, AchatModel achat, ProduitModel produit, Double quantite, Double prixUnitaireFacture) {
        this.id = id;
        this.achat = achat;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaireFacture = prixUnitaireFacture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AchatModel getAchat() {
        return achat;
    }

    public void setAchat(AchatModel achat) {
        this.achat = achat;
    }

    public ProduitModel getProduit() {
        return produit;
    }

    public void setProduit(ProduitModel produit) {
        this.produit = produit;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaireFacture() {
        return prixUnitaireFacture;
    }

    public void setPrixUnitaireFacture(Double prixUnitaireFacture) {
        this.prixUnitaireFacture = prixUnitaireFacture;
    }

    @Override
    public String toString() {
        return "AchatDetailsModel{" +
                "id=" + id +
                ", achat=" + (achat != null ? achat.getId() : null) +
                ", produit=" + (produit != null ? produit.getNomProduit() : null) +
                ", quantite=" + quantite +
                ", prixUnitaireFacture=" + prixUnitaireFacture +
                '}';
    }
}