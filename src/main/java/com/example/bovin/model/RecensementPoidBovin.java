package com.example.bovin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recensement_poid_bovin")
public class RecensementPoidBovin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_bovin" ,nullable = false)
    private BovinModel bovin ;

    @Column(name = "poid" ,nullable = false)
    private Double poid ;

    @Column(name = "date_recensement",nullable = false)
    private LocalDate dateRecensement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BovinModel getBovin() {
        return bovin;
    }

    public void setBovin(BovinModel bovin) {
        this.bovin = bovin;
    }

    public Double getPoid() {
        return poid;
    }

    public void setPoid(Double poid) {
        this.poid = poid;
    }

    public LocalDate getDateRecensement() {
        return dateRecensement;
    }

    public void setDateRecensement(LocalDate dateRecensement) {
        this.dateRecensement = dateRecensement;
    }
}
