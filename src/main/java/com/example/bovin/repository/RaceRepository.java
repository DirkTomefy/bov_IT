package com.example.bovin.repository;

import com.example.bovin.model.RaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<RaceModel, Integer> {

    Optional<RaceModel> findByLibelle(String libelle);

}