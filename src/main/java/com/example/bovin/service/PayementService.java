package com.example.bovin.service;

import com.example.bovin.model.TypePayementModel;
import com.example.bovin.model.EtatPayementModel;
import com.example.bovin.repository.TypePayementRepository;
import com.example.bovin.repository.EtatPayementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayementService {

    private final TypePayementRepository typePayementRepository;
    private final EtatPayementRepository etatPayementRepository;

    public PayementService(TypePayementRepository typePayementRepository, EtatPayementRepository etatPayementRepository) {
        this.typePayementRepository = typePayementRepository;
        this.etatPayementRepository = etatPayementRepository;
    }

    public List<TypePayementModel> getAllTypePayements() {
        return typePayementRepository.findAll();
    }

    public List<EtatPayementModel> getAllEtatPayements() {
        return etatPayementRepository.findAll();
    }
}