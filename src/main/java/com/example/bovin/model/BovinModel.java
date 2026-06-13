package com.example.bovin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bovin")
public class BovinModel extends AbstractBovin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
