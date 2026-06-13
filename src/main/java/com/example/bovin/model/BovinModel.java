package com.example.bovin.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bovin")
public class BovinModel extends AbstractBovin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "bovin", cascade = CascadeType.REMOVE)
    private List<RecensementPoidBovin> recensementPoidBovins;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RecensementPoidBovin> getRecensementPoidBovins() {
        return recensementPoidBovins;
    }

    public void setRecensementPoidBovins(List<RecensementPoidBovin> recensementPoidBovins) {
        this.recensementPoidBovins = recensementPoidBovins;
    }
}