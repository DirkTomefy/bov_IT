package com.example.bovin.service;

import com.example.bovin.model.RaceModel;
import com.example.bovin.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    public RaceModel save(RaceModel race) {
        return raceRepository.save(race);
    }

    public RaceModel update(Integer id, RaceModel race) {
        RaceModel existingRace = raceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Race introuvable avec l'id : " + id));

        existingRace.setLibelle(race.getLibelle());

        return raceRepository.save(existingRace);
    }

    public List<RaceModel> getAll() {
        return raceRepository.findAll();
    }

    public void delete(Integer id) {
        if (!raceRepository.existsById(id)) {
            throw new RuntimeException("Race introuvable avec l'id : " + id);
        }

        raceRepository.deleteById(id);
    }
}