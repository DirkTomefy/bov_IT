package com.example.bovin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "achat")
public class AchatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur", nullable = false)
    private FournisseurModel fournisseur;

    @Column(name = "date_achat", nullable = false, updatable = false)
    private LocalDateTime dateAchat = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_type_payement", nullable = false)
    private TypePayementModel typePayement;

    @ManyToOne
    @JoinColumn(name = "id_etat_payement", nullable = false)
    private EtatPayementModel etatPayement;

    @Column(name = "prix_total", nullable = false)
    private Double prixTotal;

    public AchatModel() {
    }

    public AchatModel(FournisseurModel fournisseur, TypePayementModel typePayement, EtatPayementModel etatPayement, Double prixTotal) {
        this.fournisseur = fournisseur;
        this.typePayement = typePayement;
        this.etatPayement = etatPayement;
        this.prixTotal = prixTotal;
    }

    public AchatModel(Integer id, FournisseurModel fournisseur, LocalDateTime dateAchat, TypePayementModel typePayement, EtatPayementModel etatPayement, Double prixTotal) {
        this.id = id;
        this.fournisseur = fournisseur;
        this.dateAchat = dateAchat;
        this.typePayement = typePayement;
        this.etatPayement = etatPayement;
        this.prixTotal = prixTotal;
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

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public TypePayementModel getTypePayement() {
        return typePayement;
    }

    public void setTypePayement(TypePayementModel typePayement) {
        this.typePayement = typePayement;
    }

    public EtatPayementModel getEtatPayement() {
        return etatPayement;
    }

    public void setEtatPayement(EtatPayementModel etatPayement) {
        this.etatPayement = etatPayement;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "AchatModel{" +
                "id=" + id +
                ", fournisseur=" + (fournisseur != null ? fournisseur.getId() : null) +
                ", dateAchat=" + dateAchat +
                ", typePayement=" + typePayement +
                ", etatPayement=" + etatPayement +
                ", prixTotal=" + prixTotal +
                '}';
    }
}