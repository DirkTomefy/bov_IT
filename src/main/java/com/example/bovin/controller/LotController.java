package com.example.bovin.controller;

import com.example.bovin.model.LotModel;
import com.example.bovin.model.RaceModel;
import com.example.bovin.service.LotService; // Remplacez par votre repository ou service actuel
import com.example.bovin.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/lot")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private RaceService raceService;

    @GetMapping("/list")
    public String listLots(Model model) {
        List<LotModel> lots = lotService.getAll();
        model.addAttribute("lots", lots);
        return "lot/list";
    }

    @GetMapping("/creer")
    public String showCreateForm(Model model) {
        List<RaceModel> races = raceService.getAll();
        model.addAttribute("lot", new LotModel());
        model.addAttribute("races", races);
        model.addAttribute("action", "/lot/creer");
        model.addAttribute("isEdit", false);
        return "lot/form";
    }

    @PostMapping("/creer")
    public String createLot(@ModelAttribute("lot") LotModel lot) {
        if (lot.getDateCreation() == null) {
            lot.setDateCreation(LocalDateTime.now());
        }
        lotService.save(lot);
        return "redirect:/lot/list";
    }

    @GetMapping("/modif/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        LotModel lot = lotService.getById(id); // Adaptez selon votre gestion d'exception
        List<RaceModel> races = raceService.getAll();
        
        model.addAttribute("lot", lot);
        model.addAttribute("races", races);
        model.addAttribute("action", "/lot/modif/" + id);
        model.addAttribute("isEdit", true);
        return "lot/form";
    }

    @PostMapping("/modif/{id}")
    public String updateLot(@PathVariable Integer id, @ModelAttribute("lot") LotModel lot) {
        if (lot.getDateCreation() == null) {
            // Optionnel : Conserver l'ancienne date ou mettre date.now()
            lot.setDateCreation(LocalDateTime.now()); 
        }
        lotService.update(id, lot);
        return "redirect:/lot/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteLot(@PathVariable Integer id) {
        lotService.delete(id);
        return "redirect:/lot/list";
    }
}