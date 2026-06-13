package com.example.bovin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractBovin {

    @Column(name = "poids_init", nullable = false)
    private Double poidsInit;

    @ManyToOne
    @JoinColumn(name = "id_lot", nullable = false)
    private LotModel lot;

    @Column(name = "mois_init", nullable = false)
    private Integer moisInit;

    @Column(name = "date_arrive")
    private LocalDate dateArrive;

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
                ", poidsInit=" + poidsInit +
                ", lot=" + lot +
                ", moisInit=" + moisInit +
                ", dateArrive=" + dateArrive +
                '}';
    }
}
