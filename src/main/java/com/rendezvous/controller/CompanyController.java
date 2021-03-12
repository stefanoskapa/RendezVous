/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.service.CompanyService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/company")
public class CompanyController {
    
    
    @Autowired
    CompanyService companyService;
    
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "company/dashboard_company";
    }
    
    @GetMapping("/profile")
    public String showProfile(@ModelAttribute("company") Company company) {
        
        //todo
//        company = //i logarismeni company. Tha ton xrisomopoiei i forma diorthoseis stoixeion tis eterias sto profile_company
        return "company/profile_company";
    }
    
//    @PostMapping("/profile")
//    public String updateProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        //ananeosi tis company stin vasi
//        return "redirect:company/dashboard";
//    }
    
    
    @GetMapping("/business-hours")
    public String showProfile() {
        
        //todo
//        List me working hours = //i logarismeni company. Tha ton xrisomopoiei i forma diorthosis business hours tis eterias sto business_hours
        return "company/business_hours";
    }
    
//    @PostMapping("/business-hours")
//    public String updateProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        //ananeosi tis company stin vasi
//        return "redirect:company/business_hours";
//    }
    
    @ModelAttribute
    public void addAttributes(Principal principal, Model model) {

        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            model.addAttribute("company_name", c.getDisplayName());
        }
    }
    
    
    
    
}
