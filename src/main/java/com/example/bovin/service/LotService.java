package com.example.bovin.service;

import com.example.bovin.model.LotModel;
import com.example.bovin.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    public LotModel save(LotModel lot) {
        if (lot.getDateCreation() == null) {
            lot.setDateCreation(LocalDateTime.now());
        }
        return lotRepository.save(lot);
    }

    public LotModel update(Integer id, LotModel lot) {
        LotModel existingLot = lotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lot introuvable avec l'id : " + id));

        existingLot.setRace(lot.getRace());
        existingLot.setNombre(lot.getNombre());
        
        // Si une date est fournie, on la met à jour, sinon on garde l'ancienne ou met now()
        if (lot.getDateCreation() != null) {
            existingLot.setDateCreation(lot.getDateCreation());
        } else if (existingLot.getDateCreation() == null) {
            existingLot.setDateCreation(LocalDateTime.now());
        }

        return lotRepository.save(existingLot);
    }

    public List<LotModel> getAll() {
        return lotRepository.findAll();
    }

    public LotModel getById(Integer id) {
        return lotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lot introuvable avec l'id : " + id));
    }

    public void delete(Integer id) {
        if (!lotRepository.existsById(id)) {
            throw new RuntimeException("Lot introuvable avec l'id : " + id);
        }
        lotRepository.deleteById(id);
    }
}