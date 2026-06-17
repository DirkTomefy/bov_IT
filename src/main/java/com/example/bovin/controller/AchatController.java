package com.example.bovin.controller;

import com.example.bovin.model.AchatModel;
import com.example.bovin.model.AchatDetailsModel;
import com.example.bovin.model.FournisseurModel;
import com.example.bovin.model.TypeFournisseurModel;
import com.example.bovin.service.AchatService;
import com.example.bovin.service.AchatDetailsService;
import com.example.bovin.service.FournisseurService;
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
@RequestMapping("/achats")
public class AchatController {

    private final AchatService achatService;
    private final AchatDetailsService achatDetailsService;
    private final FournisseurService fournisseurService;
    private final TypeFournisseurService typeFournisseurService;

    // Injection unique de tous les composants nécessaires
    public AchatController(AchatService achatService, 
                           AchatDetailsService achatDetailsService,
                           FournisseurService fournisseurService, 
                           TypeFournisseurService typeFournisseurService) {
        this.achatService = achatService;
        this.achatDetailsService = achatDetailsService;
        this.fournisseurService = fournisseurService;
        this.typeFournisseurService = typeFournisseurService;
    }

    /**
     * Affiche la liste des achats avec filtres croisés.
     * Routes possibles : 
     * - /achats (Tout afficher)
     * - /achats?typeFournisseurId=2 (Filtrer par type ex: Aliments)
     * - /achats?fournisseurId=5 (Filtrer par un fournisseur précis)
     */
    @GetMapping
    public String listeAchats(@RequestParam(name = "typeFournisseurId", required = false) Integer typeFournisseurId,
                              @RequestParam(name = "fournisseurId", required = false) Integer fournisseurId,
                              Model model) {
        List<AchatModel> achats;

        // Logique de filtrage en cascade
        if (fournisseurId != null) {
            achats = achatService.getAchatsByFournisseur(fournisseurId);
        } else if (typeFournisseurId != null) {
            achats = achatService.getAchatsByTypeFournisseur(typeFournisseurId);
        } else {
            achats = achatService.getAllAchats();
        }

        // On remonte les données pour remplir les listes déroulantes ou les filtres de la vue
        List<TypeFournisseurModel> typesFournisseur = typeFournisseurService.getAllTypes();
        List<FournisseurModel> fournisseurs = fournisseurService.getAllFournisseurs();

        model.addAttribute("achats", achats);
        model.addAttribute("typesFournisseur", typesFournisseur);
        model.addAttribute("fournisseurs", fournisseurs);
        
        // On renvoie les IDs sélectionnés pour pré-sélectionner les filtres dans le HTML
        model.addAttribute("selectedTypeId", typeFournisseurId);
        model.addAttribute("selectedFournisseurId", fournisseurId);

        return "achat/liste"; // Fichier HTML : src/main/resources/templates/achat/liste.html
    }

    /**
     * Affiche le détail complet d'une facture d'achat (Le bon de commande / reçu).
     * Route : /achats/details/12
     */
    @GetMapping("/details/{id}")
    public String detailsAchat(@PathVariable("id") Integer id, Model model) {
        Optional<AchatModel> achatOpt = achatService.getAchatById(id);

        if (achatOpt.isEmpty()) {
            return "redirect:/achats";
        }

        AchatModel achat = achatOpt.get();
        List<AchatDetailsModel> lignesDetails = achatDetailsService.getDetailsByAchatId(id);

        model.addAttribute("achat", achat);
        model.addAttribute("lignesDetails", lignesDetails);

        return "achat/details"; // Fichier HTML : src/main/resources/templates/achat/details.html
    }
}