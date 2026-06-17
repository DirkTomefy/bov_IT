package com.example.bovin.service;

import com.example.bovin.model.AchatDetailsModel;
import com.example.bovin.repository.AchatDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchatDetailsService {

    private final AchatDetailsRepository achatDetailsRepository;

    public AchatDetailsService(AchatDetailsRepository achatDetailsRepository) {
        this.achatDetailsRepository = achatDetailsRepository;
    }

    // Récupérer toutes les lignes d'un achat spécifique
    public List<AchatDetailsModel> getDetailsByAchatId(Integer idAchat) {
        return achatDetailsRepository.findByAchatId(idAchat);
    }
}