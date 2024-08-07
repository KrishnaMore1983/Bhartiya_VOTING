package com.voatingsys.controller;

import org.springframework.jdbc.core.JdbcTemplate;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.voatingsys.entity.Admin;
import com.voatingsys.entity.Voter;
import com.voatingsys.service.ElectionService;
import com.voatingsys.service.VoterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class VoterController {
	


	@Autowired
	private VoterService voterService;
	
	@Autowired
    private ElectionService electionService;

	 
    @GetMapping("/saveHome")
    public String saveHome() {
      
        return "FrontHome"; // Ensure this is mapped to adminregister.html
    }
	
    @GetMapping("/saveVoter")
    public String saveVoter(Model model) {
        model.addAttribute("voter", new Voter());
        return "voterregister"; // Ensure this is mapped to adminregister.html
    }
    
    @GetMapping("/saveVoter2")
    public String saveVoter2(Model model) {
        model.addAttribute("voter", new Voter());
        return "voterregister2"; // Ensure this is mapped to adminregister.html
    }
    @PostMapping("/govoterregister")
    public String getVoterStatus(@Valid @ModelAttribute Voter voter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "voterregister"; // Return to the registration page if there are errors
        }
        voterService.saveVoter(voter);
        return "redirect:/aftervoterregister"; // Redirect to the login page after registration
    }
    @PostMapping("/govoterregister2")
    public String getVoterStatus2(@Valid @ModelAttribute Voter voter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "voterregister2"; // Return to the registration page if there are errors
        }
        voterService.saveVoter(voter);
        return "redirect:/aftervoterregister2"; // Redirect to the login page after registration
    }
    
    @GetMapping("/aftervoterregister")
    public String afterRegister() {
      
        return "aftervoterregister"; // Ensure this is mapped to adminregister.html
    }
    @GetMapping("/aftervoterregister2")
    public String afterRegister2() {
      
        return "aftervoterregister2"; // Ensure this is mapped to adminregister.html
    }

    @GetMapping("/voterlogin")
    public String showLoginForm(Model model) {
        model.addAttribute("voter", new Voter());
        return "voterlogin"; // Ensure this is mapped to adminlogin.html
    }

    @PostMapping("/voterlogin")
    public String login(@ModelAttribute Admin admin, Model model) {
        Voter loggedInAdmin = voterService.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (loggedInAdmin != null) {
            // Login successful, redirect to the dashboard
            return "redirect:/voterdashboard";
        } else {
            // Login failed, show error message
            model.addAttribute("error", "Invalid username or password");
            return "voterlogin"; // Return to the login page with error message
        }
    }
    

//    @GetMapping("/voterdashboard")
//    public String voterDashboard(Model model) { 
 
  //          Voter voter = voterService.getVoter(null); // Ensure this returns a non-null Voter object
    //        model.addAttribute("voter", voter);
      //      return "voterdashboard";
        
   // }
   
    
    @GetMapping("/voterdashboard")
    public String voterDashboard(Model model) {
    
        // Add election details and voter info to the model
        model.addAttribute("elections", electionService.getUpcomingElections());
       
        
        return "voterdashboard";
    }
   
    @GetMapping("/faq")
    public String faq() {
        return "faq"; 
    }
    
    @GetMapping("contactsupport")
    public String contact() {
        return "contactsupport"; 
    }
    
    @PostMapping("/submitFeedback")
    public String submitFeedback(@RequestParam("feedback") String feedback, RedirectAttributes redirectAttributes) {
        // Process the feedback (e.g., save to a database, send an email, etc.)
        System.out.println("Feedback received: " + feedback);

        // Add a success message to be displayed on redirect
        redirectAttributes.addFlashAttribute("message", "Thank you for your feedback!");

        // Redirect to the dashboard or another page
        return "redirect:/voterdashboard";
    }


    }
    

    
