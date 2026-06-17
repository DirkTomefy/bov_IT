package com.example.bovin.service;

import com.example.bovin.model.ProduitFournisseurModel;
import com.example.bovin.repository.ProduitFournisseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitFournisseurService {

    private final ProduitFournisseurRepository produitFournisseurRepository;

    public ProduitFournisseurService(ProduitFournisseurRepository produitFournisseurRepository) {
        this.produitFournisseurRepository = produitFournisseurRepository;
    }

    // Récupérer le catalogue complet d'un fournisseur spécifique
    public List<ProduitFournisseurModel> getProduitsByFournisseur(Integer idFournisseur) {
        return produitFournisseurRepository.findByFournisseurId(idFournisseur);
    }
}