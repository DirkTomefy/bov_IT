package com.example.bovin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bovin.model.BovinModel;

@Repository
public interface BovinRepository extends JpaRepository<BovinModel, Integer> {

    List<BovinModel> findByLot_Id(Integer idLot);

}