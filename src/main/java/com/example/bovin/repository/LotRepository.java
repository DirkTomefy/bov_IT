package com.example.bovin.repository;

import com.example.bovin.model.LotModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LotRepository extends JpaRepository<LotModel, Integer> {

}