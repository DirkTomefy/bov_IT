package com.example.bovin.service;

import com.example.bovin.model.BovinModel;
import com.example.bovin.repository.BovinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BovinService {

    @Autowired
    private BovinRepository bovinRepository;

    public BovinModel save(BovinModel bovin) {
      
        //-- insertion du poid actuel du bovin ---

        if (bovin.getDateArrive() == null) {             // règle 3
            bovin.setDateArrive(LocalDate.now());
        }
        return bovinRepository.save(bovin);
    }

    public BovinModel update(Integer id, BovinModel bovin) {
        
        //recuperer le bovin s il existe
        BovinModel existingBovin = bovinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bovin introuvable avec l'id : " + id));


        //verification du chagmement de poid 
        //changement de poid si changer
        //verification changement de lot 
        //changemnt de lot si changer
        existingBovin.setPoidsInit(bovin.getPoidsInit());
        existingBovin.setMoisInit(bovin.getMoisInit());
        existingBovin.setDateArrive(bovin.getDateArrive());

        return bovinRepository.save(existingBovin);
    }

    public List<BovinModel> getAll() {
        return bovinRepository.findAll();
    }


    public BovinModel findById(Integer id) {
        return bovinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bovin introuvable : " + id));
    }

    public void delete(Integer id) {
        if (!bovinRepository.existsById(id)) {
            throw new RuntimeException("Bovin introuvable avec l'id : " + id);
        }

        bovinRepository.deleteById(id);
    }
}