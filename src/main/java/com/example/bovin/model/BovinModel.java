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

    @Column(name = "poids_actuel", nullable = false)
    private Double poidsActuel;

    @ManyToOne
    @JoinColumn(name = "id_lot", nullable = false)
    private LotModel lot;

    @Column(name = "mois_init", nullable = false)
    private Integer moisInit;

    @Column(name = "mois_actuel", nullable = false)
    private Integer moisActuel;

    @Column(name = "date_arrive")
    private LocalDate dateArrive;

    public BovinModel() {
    }

    public BovinModel(Double poidsInit, Double poidsActuel, LotModel lot, Integer moisInit, Integer moisActuel, LocalDate dateArrive) {
        this.poidsInit = poidsInit;
        this.poidsActuel = poidsActuel;
        this.lot = lot;
        this.moisInit = moisInit;
        this.moisActuel = moisActuel;
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

    public Double getPoidsActuel() {
        return poidsActuel;
    }

    public void setPoidsActuel(Double poidsActuel) {
        this.poidsActuel = poidsActuel;
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

    public Integer getMoisActuel() {
        return moisActuel;
    }

    public void setMoisActuel(Integer moisActuel) {
        this.moisActuel = moisActuel;
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
                ", poidsActuel=" + poidsActuel +
                ", lot=" + lot +
                ", moisInit=" + moisInit +
                ", moisActuel=" + moisActuel +
                ", dateArrive=" + dateArrive +
                '}';
    }
}
