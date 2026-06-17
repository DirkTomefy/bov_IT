package com.example.bovin.repository;

import com.example.bovin.model.ProduitModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitRepository extends JpaRepository<ProduitModel, Integer> {

}