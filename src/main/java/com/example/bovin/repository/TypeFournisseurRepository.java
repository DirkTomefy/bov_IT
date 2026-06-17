package com.example.bovin.repository;

import com.example.bovin.model.TypeFournisseurModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeFournisseurRepository extends JpaRepository<TypeFournisseurModel, Integer> {

}