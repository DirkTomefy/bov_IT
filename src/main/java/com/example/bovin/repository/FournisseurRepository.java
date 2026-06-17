package com.example.bovin.repository;

import com.example.bovin.model.FournisseurModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurModel, Integer> {

    List<FournisseurModel> findByTypeFournisseurId(Integer idTypeFournisseur);

}