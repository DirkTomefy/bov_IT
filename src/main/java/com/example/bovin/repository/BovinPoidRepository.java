package com.example.bovin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bovin.model.vue.VueBovinPoidActuel;

public interface BovinPoidRepository extends JpaRepository<VueBovinPoidActuel,Integer> {
    
}
