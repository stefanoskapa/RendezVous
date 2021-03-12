package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    

    @GetMapping("/client-register")
    public String showClientRegistration(@ModelAttribute("newClient") Client newClient, Model model) {
        return "client/register_client";
    }

    @PostMapping("/client-register")
    public String clientRegistration(@Valid @ModelAttribute("newClient") Client newClient, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/client/register_client";
        }
        if (userRepository.findByEmail(newClient.getUser().getEmail()).isPresent()) {
            model.addAttribute("userExistsError", "This email is already being used!");
            model.addAttribute("newClient", new Client());
            return "client/register_client";
        }
        clientService.saveClient(newClient);
        
        return "redirect:/";
    }

    @GetMapping("/company-register")
    public String showCompanyRegistration(@ModelAttribute("newCompany") Company newCompany, Model model) {
        return "company/register_company";
    }

    @PostMapping("/company-register")
    public String companyRegistration(@Valid @ModelAttribute("newCompany") Company newCompany, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "company/register_company";
        }
        if (userRepository.findByEmail(newCompany.getUser().getEmail()).isPresent()) {
            model.addAttribute("userExistsError", "This email is already being used!");
            model.addAttribute("newCompany", new Company());
            return "company/register_company";
        }
        
        companyService.saveCompany(newCompany);
        return "redirect:/login";

    }

}
