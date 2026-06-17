package com.example.bovin.repository;

import com.example.bovin.model.EtatPayementModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EtatPayementRepository extends JpaRepository<EtatPayementModel, Integer> {

}