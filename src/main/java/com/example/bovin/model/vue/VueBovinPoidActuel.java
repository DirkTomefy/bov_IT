package com.example.bovin.model.vue;

import org.hibernate.annotations.Immutable;

import com.example.bovin.model.AbstractBovin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_bovin_poid_actuel")
@Immutable
public class VueBovinPoidActuel extends AbstractBovin {

    @Id
    private Integer id;

   

    @Column(name = "poid_actuel")
    private Double poidsActuel;

    
 public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPoidsActuel() {
        return poidsActuel;
    }

    public void setPoidsActuel(Double poidsActuel) {
        this.poidsActuel = poidsActuel;
    }
}
