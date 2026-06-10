package com.example.bovin.repository;

import com.example.bovin.model.BovinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BovinRepository extends JpaRepository<BovinModel, Integer> {

}