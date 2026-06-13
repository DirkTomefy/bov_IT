package com.example.bovin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bovin.model.BovinModel;
import com.example.bovin.repository.BovinRepository;

@Service
public class BovinService {

    @Autowired
    private BovinRepository bovinRepository;

    public BovinModel save(BovinModel bovin) {
        bovin.setPoidsActuel(bovin.getPoidsInit());      // règle 1
        bovin.setMoisActuel(bovin.getMoisInit());        // règle 2
        if (bovin.getDateArrive() == null) {             // règle 3
            bovin.setDateArrive(LocalDate.now());
        }
        return bovinRepository.save(bovin);
    }

    public BovinModel update(Integer id, BovinModel bovin) {
        BovinModel existingBovin = bovinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bovin introuvable avec l'id : " + id));

        existingBovin.setPoidsInit(bovin.getPoidsInit());
        existingBovin.setPoidsActuel(bovin.getPoidsActuel());
        existingBovin.setLot(bovin.getLot());
        existingBovin.setMoisInit(bovin.getMoisInit());
        existingBovin.setMoisActuel(bovin.getMoisActuel());
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

    // Récupère la liste des bovins appartenant à un lot donné
    public List<BovinModel> getByLotId(Integer lotId) {
        return bovinRepository.findByLot_Id(lotId);
    }

    // Met le lot du bovin à null (le bovin n'est plus rattaché à aucun lot)
    public BovinModel removeLotFromBovin(Integer bovinId) {
        BovinModel bovin = bovinRepository.findById(bovinId)
                .orElseThrow(() -> new RuntimeException("Bovin introuvable avec l'id : " + bovinId));

        bovin.setLot(null);

        return bovinRepository.save(bovin);
    }
}