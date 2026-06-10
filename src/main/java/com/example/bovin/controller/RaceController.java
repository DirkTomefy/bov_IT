package com.example.bovin.controller;

import com.example.bovin.model.RaceModel;
import com.example.bovin.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/race")
public class RaceController {

    @Autowired
    private RaceService raceService;

    
    @GetMapping("/list")
    public String listRaces(Model model) {
        List<RaceModel> races = raceService.getAll();
        model.addAttribute("races", races);
        return "race/list";
    }

    
    @GetMapping("/creer")
    public String showCreateForm(Model model) {
        model.addAttribute("race", new RaceModel());
        model.addAttribute("action", "/race/creer");
        model.addAttribute("isEdit", false);
        return "race/form";
    }

    
    @PostMapping("/creer")
    public String createRace(@ModelAttribute("race") RaceModel race) {
        raceService.save(race);
        return "redirect:/race/list";
    }

    
    @GetMapping("/modif/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        RaceModel race = raceService.getAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Race introuvable avec l'id : " + id));
        model.addAttribute("race", race);
        model.addAttribute("action", "/race/modif/" + id);
        model.addAttribute("isEdit", true);
        return "race/form";
    }

    
    @PostMapping("/modif/{id}")
    public String updateRace(@PathVariable Integer id, @ModelAttribute("race") RaceModel race) {
        raceService.update(id, race);
        return "redirect:/race/list";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteRace(@PathVariable Integer id) {
        raceService.delete(id);
        return "redirect:/race/list";
    }
}