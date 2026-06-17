package com.example.bovin.repository;

import com.example.bovin.model.AchatModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AchatRepository extends JpaRepository<AchatModel, Integer> {

    List<AchatModel> findByFournisseurId(Integer idFournisseur);

    List<AchatModel> findByFournisseurTypeFournisseurId(Integer idTypeFournisseur);

}