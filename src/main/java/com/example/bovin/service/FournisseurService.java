package com.example.bovin.service;

import com.example.bovin.model.FournisseurModel;
import com.example.bovin.repository.FournisseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    // Récupérer tous les fournisseurs (si aucun filtre n'est appliqué)
    public List<FournisseurModel> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    // FILTRE : Récupérer les fournisseurs selon l'ID du type (ex: Bovins, Aliments)
    public List<FournisseurModel> getFournisseursByType(Integer idTypeFournisseur) {
        return fournisseurRepository.findByTypeFournisseurId(idTypeFournisseur);
    }

    // DETAILS : Trouver un fournisseur par son ID
    public Optional<FournisseurModel> getFournisseurById(Integer id) {
        return fournisseurRepository.findById(id);
    }
}