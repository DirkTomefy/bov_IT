package com.example.bovin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bovin.model.vue.VueBovinPoidsMoisActuel;

public interface BovinPoidMoisRepository extends JpaRepository<VueBovinPoidsMoisActuel,Integer> {
    List<VueBovinPoidsMoisActuel> findAllByLotId(Integer idLot);
}
