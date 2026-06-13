package com.example.bovin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bovin.model.vue.VueBovinPoidsMoisActuel;

public interface BovinPoidMoisRepository extends JpaRepository<VueBovinPoidsMoisActuel,Integer> {
    
}
