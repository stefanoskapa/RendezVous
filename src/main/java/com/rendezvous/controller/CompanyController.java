/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/customer")
public class CompanyController {
    
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "company/dasbboard_company";
    }
    
//    @GetMapping("/profile")
//    public String showProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        company = //i logarismeni company. Tha ton xrisomopoiei i forma diorthoseis stoixeion tis eterias sto profile_company
//        return "company/profile_company";
//    }
    
//    @PostMapping("/profile")
//    public String updateProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        //ananeosi tis company stin vasi
//        return "redirect:company/dashboard";
//    }
    
    
//    @GetMapping("/business-hours")
//    public String showProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        company = //i logarismeni company. Tha ton xrisomopoiei i forma diorthosis business hours tis eterias sto business_hours
//        return "company/business_hours";
//    }
    
//    @PostMapping("/business-hours")
//    public String updateProfile(@ModelAttribute("company") Company company) {
//        
//        //todo
//        //ananeosi tis company stin vasi
//        return "redirect:company/business_hours";
//    }
    
    
    
    
}
