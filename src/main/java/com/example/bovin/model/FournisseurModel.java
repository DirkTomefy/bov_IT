package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fournisseur")
public class FournisseurModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fournisseur")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_type_fournisseur", nullable = false)
    private TypeFournisseurModel typeFournisseur;

    @Column(name = "nom_interlocuteur", length = 100)
    private String nomInterlocuteur;

    @Column(length = 20)
    private String telephone;

    @Column(name = "e_mail", length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    public FournisseurModel() {
    }

    public FournisseurModel(String nom, TypeFournisseurModel typeFournisseur, String nomInterlocuteur, String telephone, String email, String adresse) {
        this.nom = nom;
        this.typeFournisseur = typeFournisseur;
        this.nomInterlocuteur = nomInterlocuteur;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public FournisseurModel(Integer id, String nom, TypeFournisseurModel typeFournisseur, String nomInterlocuteur, String telephone, String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.typeFournisseur = typeFournisseur;
        this.nomInterlocuteur = nomInterlocuteur;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeFournisseurModel getTypeFournisseur() {
        return typeFournisseur;
    }

    public void setTypeFournisseur(TypeFournisseurModel typeFournisseur) {
        this.typeFournisseur = typeFournisseur;
    }

    public String getNomInterlocuteur() {
        return nomInterlocuteur;
    }

    public void setNomInterlocuteur(String nomInterlocuteur) {
        this.nomInterlocuteur = nomInterlocuteur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "FournisseurModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeFournisseur=" + typeFournisseur +
                ", nomInterlocuteur='" + nomInterlocuteur + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}