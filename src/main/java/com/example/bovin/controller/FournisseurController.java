package com.example.bovin.controller;

import com.example.bovin.model.FournisseurModel;
import com.example.bovin.model.ProduitFournisseurModel;
import com.example.bovin.model.TypeFournisseurModel;
import com.example.bovin.service.FournisseurService;
import com.example.bovin.service.ProduitFournisseurService;
import com.example.bovin.service.TypeFournisseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

    private final FournisseurService fournisseurService;
    private final TypeFournisseurService typeFournisseurService;
    private final ProduitFournisseurService produitFournisseurService;

    // Un seul constructeur pour injecter les 3 services nécessaires
    public FournisseurController(FournisseurService fournisseurService, 
                                 TypeFournisseurService typeFournisseurService, 
                                 ProduitFournisseurService produitFournisseurService) {
        this.fournisseurService = fournisseurService;
        this.typeFournisseurService = typeFournisseurService;
        this.produitFournisseurService = produitFournisseurService;
    }

    /**
     * Affiche la liste des fournisseurs.
     * Route : /fournisseurs ou /fournisseurs?typeId=2
     */
    @GetMapping
    public String listeFournisseurs(@RequestParam(name = "typeId", required = false) Integer typeId, Model model) {
        List<FournisseurModel> fournisseurs;

        // Si un ID de type est passé en paramètre, on filtre, sinon on prend tout
        if (typeId != null) {
            fournisseurs = fournisseurService.getFournisseursByType(typeId);
        } else {
            fournisseurs = fournisseurService.getAllFournisseurs();
        }

        // On récupère aussi tous les types pour alimenter la barre de filtrage/onglets dans la vue
        List<TypeFournisseurModel> types = typeFournisseurService.getAllTypes();

        model.addAttribute("fournisseurs", fournisseurs);
        model.addAttribute("types", types);
        model.addAttribute("selectedTypeId", typeId); // Pour garder l'onglet actif visuellement

        return "fournisseur/liste"; // Nom de ton fichier HTML (Thymeleaf)
    }

    

    /**
     * Affiche les détails complets d'un fournisseur sélectionné ainsi que son catalogue de produits.
     * Route : /fournisseurs/details/5
     */
    @GetMapping("/details/{id}")
    public String detailsFournisseur(@PathVariable("id") Integer id, Model model) {
        Optional<FournisseurModel> fournisseurOpt = fournisseurService.getFournisseurById(id);

        if (fournisseurOpt.isEmpty()) {
            // Si le fournisseur n'existe pas, on redirige vers la liste principale
            return "redirect:/fournisseurs";
        }

        // Récupération du fournisseur et de la liste de ses produits tarifés
        FournisseurModel fournisseur = fournisseurOpt.get();
        List<ProduitFournisseurModel> catalogue = produitFournisseurService.getProduitsByFournisseur(id);

        model.addAttribute("fournisseur", fournisseur);
        model.addAttribute("catalogue", catalogue);

        return "fournisseur/details"; // Nom de ton fichier HTML pour les détails
    }
}