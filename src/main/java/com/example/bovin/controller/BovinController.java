package com.example.bovin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bovin.model.BovinModel;
import com.example.bovin.model.LotModel;
import com.example.bovin.service.BovinService;
import com.example.bovin.service.LotService;


@Controller
@RequestMapping("/bovin")
public class BovinController {

    @Autowired
    private BovinService bovinService;

    @Autowired
    private LotService lotService;

    
    @GetMapping("/list")
    public String listBovins(Model model) {
        List<BovinModel> bovins = bovinService.getAll();
        model.addAttribute("bovins", bovins);
        return "bovin/list";
    }

    
    @GetMapping("/creer")
    public String showCreateForm(Model model) {
        List<LotModel> lots = lotService.getAll();
        model.addAttribute("bovin", new BovinModel());
        model.addAttribute("lots", lots);
        model.addAttribute("action", "/bovin/creer");
        model.addAttribute("isEdit", false);
        return "bovin/form";
    }

    
    @PostMapping("/creer")
    public String createBovin(@ModelAttribute("bovin") BovinModel bovin) {
        bovinService.save(bovin);
        return "redirect:/bovin/list";
    }

    
    @GetMapping("/modif/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        BovinModel bovin = bovinService.findById(id);
        List<LotModel> lots = lotService.getAll();
        model.addAttribute("bovin", bovin);
        model.addAttribute("lots", lots);
        model.addAttribute("action", "/bovin/modif/" + id);
        model.addAttribute("isEdit", true);
        return "bovin/form";
    }

    
    @PostMapping("/modif/{id}")
    public String updateBovin(@PathVariable Integer id, @ModelAttribute("bovin") BovinModel bovin) {
        bovinService.update(id, bovin);
        return "redirect:/bovin/list";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteBovin(@PathVariable Integer id) {
        bovinService.delete(id);
        return "redirect:/bovin/list";
    }

    // Retire le lot d'un bovin (le bovin n'est pas supprimé, juste détaché du lot)
    @GetMapping("/retirer-lot/{id}")
    public String retirerLotDuBovin(@PathVariable Integer id, @RequestParam Integer lotId) {
        bovinService.removeLotFromBovin(id);
        return "redirect:/lot/detail/" + lotId;
    }
}