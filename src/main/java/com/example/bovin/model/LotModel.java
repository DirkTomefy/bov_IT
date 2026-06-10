package com.example.bovin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lot")
public class LotModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_race", nullable = false)
    private RaceModel race;

    @Column(nullable = false)
    private Integer nombre;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    public LotModel() {
    }

    public LotModel(RaceModel race, Integer nombre, LocalDateTime dateCreation) {
        this.race = race;
        this.nombre = nombre;
        this.dateCreation = dateCreation;
    }

    // Cycle de vie JPA : Assure la date actuelle si vide avant l'insertion
    @PrePersist
    protected void onCreate() {
        if (this.dateCreation == null) {
            this.dateCreation = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RaceModel getRace() {
        return race;
    }

    public void setRace(RaceModel race) {
        this.race = race;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "LotModel{" +
                "id=" + id +
                ", race=" + race +
                ", nombre=" + nombre +
                ", dateCreation=" + dateCreation +
                '}';
    }
}