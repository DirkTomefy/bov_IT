package com.example.bovin.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bovin")
public class BovinModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poids_init", nullable = false)
    private Double poidsInit;

    @ManyToOne
    @JoinColumn(name = "id_lot", nullable = false)
    private LotModel lot;

    @Column(name = "mois_init", nullable = false)
    private Integer moisInit;

    @Column(name = "date_arrive")
    private LocalDate dateArrive;

    public BovinModel() {
    }

    public BovinModel(Double poidsInit, Double poidsActuel, LotModel lot, Integer moisInit, Integer moisActuel, LocalDate dateArrive) {
        this.poidsInit = poidsInit;
        this.lot = lot;
        this.moisInit = moisInit;
        this.dateArrive = dateArrive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPoidsInit() {
        return poidsInit;
    }

    public void setPoidsInit(Double poidsInit) {
        this.poidsInit = poidsInit;
    }

    public LotModel getLot() {
        return lot;
    }

    public void setLot(LotModel lot) {
        this.lot = lot;
    }

    public Integer getMoisInit() {
        return moisInit;
    }

    public void setMoisInit(Integer moisInit) {
        this.moisInit = moisInit;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDate dateArrive) {
        this.dateArrive = dateArrive;
    }

    @Override
    public String toString() {
        return "BovinModel{" +
                "id=" + id +
                ", poidsInit=" + poidsInit +
                ", lot=" + lot +
                ", moisInit=" + moisInit +
                ", dateArrive=" + dateArrive +
                '}';
    }
}
