package com.example.bovin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bovin.model.RecensementPoidBovin;

@Repository
public interface RecensementPoidBovinRepository extends JpaRepository<RecensementPoidBovin, Integer> {
    Optional<RecensementPoidBovin> findFirstByBovinIdOrderByDateRecensementDesc(Integer idBovin);
}
