package com.voatingsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.voatingsys.entity.Admin;
import com.voatingsys.entity.Voter;
import com.voatingsys.service.AdminService;
import com.voatingsys.service.ElectionService;
import com.voatingsys.service.VoterService;

import jakarta.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VoterService voterService;

    @Autowired
    private ElectionService electionService;
    
    @GetMapping("/saveAdmin")
    public String saveAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminregister"; // Ensure this is mapped to adminregister.html
    }

    @PostMapping("/goadminregister")
    public String getAdminStatus(@Valid @ModelAttribute Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adminregister"; // Return to the registration page if there are errors
        }
        adminService.saveAdmin(admin);
        return "redirect:/afteradminregister"; // Redirect to the login page after registration
    }
	 
    @GetMapping("/afteradminregister")
    public String afterRegister() {
      
        return "afteradminregister"; // Ensure this is mapped to adminregister.html
    }

    @GetMapping("/adminlogin")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminlogin"; // Ensure this is mapped to adminlogin.html
    }

    @PostMapping("/adminlogin")
    public String login(@ModelAttribute Admin admin, Model model) {
        Admin loggedInAdmin = adminService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            // Login successful, redirect to the dashboard
            return "redirect:/admindashboard";
        } else {
            // Login failed, show error message
            model.addAttribute("error", "Invalid username or password");
            return "adminlogin"; // Return to the login page with error message
        }
    }

    @GetMapping("/admindashboard")
    public String showAdminDashboard(Model model) {
    	
    	List<Voter> voters = voterService.voterlist();
        model.addAttribute("voters", voters);
        // Add election details and voter info to the model
        model.addAttribute("elections", electionService.getUpcomingElections());
    
  
        // This method must return the name of the template, `admindashboard.html`
        return "admindashboard";
    }
    
    @RequestMapping(value = "/admin/voters/delete/{id}", method = RequestMethod.POST)
    public String deleteVoter(@PathVariable Integer id) {
        voterService.deleteVoter(id);
        return "redirect:/admindashboard";
    }

    @RequestMapping(value = "/admin/voters/edit/{id}", method = RequestMethod.GET)
    public String editVoter(@PathVariable Integer id, Model model) {
        Voter voter = voterService.getVoterById(id);
        model.addAttribute("voter", voter);
        return "vote-form"; // Ensure this matches your template file name
    }
    
    @RequestMapping(value = "/admin/voters/save", method = RequestMethod.POST)
        public String saveVoter(@ModelAttribute Voter voter) {
            voterService.saveVoter(voter); // Ensure you have a save method in your service
            return "redirect:/admindashboard"; // Redirect back to the dashboard
        

    }
    
   
}

