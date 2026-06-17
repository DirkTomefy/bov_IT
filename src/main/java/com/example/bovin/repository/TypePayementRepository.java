package com.example.bovin.repository;

import com.example.bovin.model.TypePayementModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypePayementRepository extends JpaRepository<TypePayementModel, Integer> {

}