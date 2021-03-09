/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    @GetMapping("/client-register")
    public String showClientRegistration() {
        return "register_client";
    }

    @GetMapping("/company-register")
    public String showCompanyRegistration() {
        return "register_company";
    }
    
    @PostMapping("/client-register")
    public String clientRegistration(RedirectAttributes redirectAttributes) {
        //todo
        //meta apo epitixi prosthiki xristi:
        
        redirectAttributes.addAttribute("success_creation", "Your account has been succesfully created");
        
        return "redirect:/login";
    }
    
    @PostMapping("/company-register")
    public String companyRegistration(RedirectAttributes redirectAttributes) {
        //todo
        //meta apo epitixi prosthiki xristi:
        
        redirectAttributes.addAttribute("success_creation", "Your account has been succesfully created");
        
        return "redirect:/login";
    }

}
