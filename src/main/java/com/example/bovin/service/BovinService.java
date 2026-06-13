package com.example.bovin.service;

import com.example.bovin.model.BovinModel;
import com.example.bovin.model.RecensementPoidBovin;
import com.example.bovin.model.vue.VueBovinPoidsMoisActuel;
import com.example.bovin.repository.BovinPoidMoisRepository;
import com.example.bovin.repository.BovinRepository;
import com.example.bovin.repository.RecensementPoidBovinRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BovinService {

    @Autowired
    private BovinRepository bovinRepository;
    @Autowired
    private RecensementPoidBovinRepository recensementPoidBovinRepository;

    @Autowired
    private BovinPoidMoisRepository bovinPoidMoisRepository;

    public List<VueBovinPoidsMoisActuel> getAllBovinPoidMoisActuel() {
        return bovinPoidMoisRepository.findAll();
    }

    @Transactional(rollbackOn = RuntimeException.class)
    public BovinModel save(BovinModel bovin) {
        if (bovin.getDateArrive() == null) {             // règle 3
            bovin.setDateArrive(LocalDate.now());
        }
        bovinRepository.save(bovin);
        RecensementPoidBovin recensementPoidBovin = new RecensementPoidBovin(bovin,bovin.getPoidsInit(), bovin.getDateArrive());
        this.addRecensementPoidBovin(bovin.getId(),recensementPoidBovin);
        return bovin;
    }
    public void addRecensementPoidBovin(Integer idBovin,RecensementPoidBovin recensementPoidBovin) {
        //verifier si le bovin existe 
          BovinModel existingBovin = bovinRepository.findById(idBovin)
                .orElseThrow(() -> new RuntimeException("Bovin introuvable avec l'id : " + idBovin));
        recensementPoidBovin.setBovin(existingBovin);
        recensementPoidBovinRepository.save(recensementPoidBovin);
    }

    public RecensementPoidBovin getLastRecensementPoid(Integer idbovin) {
        RecensementPoidBovin recensementPoidBovin = this.recensementPoidBovinRepository.findFirstByBovinIdOrderByDateRecensementDesc(idbovin).get();
        return recensementPoidBovin;
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