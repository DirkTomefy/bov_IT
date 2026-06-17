package com.example.bovin.repository;

import com.example.bovin.model.ProduitFournisseurModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitFournisseurRepository extends JpaRepository<ProduitFournisseurModel, Integer> {

    List<ProduitFournisseurModel> findByFournisseurId(Integer idFournisseur);

}