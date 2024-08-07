package com.voatingsys.controller;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.voatingsys.entity.Election;
import com.voatingsys.service.ElectionService;


@Controller
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @GetMapping("/admin/elections")
    public String getElections(Model model) {
        List<Election> elections = electionService.getAllElections();
        model.addAttribute("elections", elections);
        return "admindashboard"; // View name for the admin dashboard
    }

    @PostMapping("/admin/elections/add")
    public String addElection(@RequestParam String name, @RequestParam Date date) {
        Election election = new Election();
        election.setName(name);
        election.setDate(date);
        electionService.saveElection(election);
        return "redirect:/admin/elections";
    }

    @PostMapping("/admin/elections/delete/{id}")
    public String deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
        return "redirect:/admin/elections";
    }

    @GetMapping("/admin/elections/edit/{id}")
    public String editElectionForm(@PathVariable Long id, Model model) {
        Election election = electionService.getElectionById(id);
        model.addAttribute("election", election);
        return "editElection"; // View name for the edit election form
    }

    @PostMapping("/admin/elections/edit")
    public String updateElection(@ModelAttribute Election election) {
        electionService.saveElection(election);
        return "redirect:/admin/elections";
    }
    
  
    
}
