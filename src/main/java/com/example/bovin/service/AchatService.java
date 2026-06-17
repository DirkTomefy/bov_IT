package com.example.bovin.service;

import com.example.bovin.model.AchatModel;
import com.example.bovin.repository.AchatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchatService {

    private final AchatRepository achatRepository;

    public AchatService(AchatRepository achatRepository) {
        this.achatRepository = achatRepository;
    }

    // Récupérer tous les achats (historique global)
    public List<AchatModel> getAllAchats() {
        return achatRepository.findAll();
    }

    // FILTRE 1 : Filtrer les achats par un Fournisseur spécifique
    public List<AchatModel> getAchatsByFournisseur(Integer idFournisseur) {
        return achatRepository.findByFournisseurId(idFournisseur);
    }

    // FILTRE 2 : Filtrer les achats par une catégorie entière (Type de fournisseur)
    public List<AchatModel> getAchatsByTypeFournisseur(Integer idTypeFournisseur) {
        return achatRepository.findByFournisseurTypeFournisseurId(idTypeFournisseur);
    }

    // Obtenir une facture spécifique par son ID
    public Optional<AchatModel> getAchatById(Integer id) {
        return achatRepository.findById(id);
    }
}