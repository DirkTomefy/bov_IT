package com.example.bovin.service;

import com.example.bovin.model.TypeFournisseurModel;
import com.example.bovin.repository.TypeFournisseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeFournisseurService {

    private final TypeFournisseurRepository typeFournisseurRepository;

    public TypeFournisseurService(TypeFournisseurRepository typeFournisseurRepository) {
        this.typeFournisseurRepository = typeFournisseurRepository;
    }

    public List<TypeFournisseurModel> getAllTypes() {
        return typeFournisseurRepository.findAll();
    }
}