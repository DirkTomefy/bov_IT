package com.example.bovin.repository;

import com.example.bovin.model.AchatDetailsModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AchatDetailsRepository extends JpaRepository<AchatDetailsModel, Integer> {

    List<AchatDetailsModel> findByAchatId(Integer idAchat);

}