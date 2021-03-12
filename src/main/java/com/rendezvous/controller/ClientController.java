/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.service.ClientService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @ModelAttribute
    public void addAttributes(Principal principal, Model model) {

        if (principal != null) {
            Client client = clientService.findClientByEmail(principal.getName());
            model.addAttribute("username", client.getFname() + " " + client.getLname());
            model.addAttribute("client", client);
        }
    }

    @GetMapping("/")
    public String redirectToDashboard() {
        return "redirect:/client/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "client/dashboard_client";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "client/profile_client";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, Model model) {
        Client loggedUser = (Client) model.getAttribute("client");

        System.out.println(client);

        if (bindingResult.hasErrors()) {
            return "client/profile_client";
        }
        
        client.setUser(loggedUser.getUser()); //making sure user havent malformed his credentials

        clientService.saveClient(client);

//        boolean logout=false;
//        
//        if (client.getUser().getEmail() != loggedUser.getUser().getEmail()) {
//            logout = true;
//        }
//        
//        System.out.println(client);
//
//        if (bindingResult.hasErrors() && !bindingResult.hasFieldErrors("user.password")) {
//            return "client/profile_client";
//        }
//
//        if (bindingResult.getErrorCount() == 1 && bindingResult.hasFieldErrors("user.password")) {
//            //save client to db without including a change in the password
//            clientService.saveClientExludingPassword(client);
//            return "redirect:/client/dashboard";
//        }
//            
//        clientService.saveClient(client);
//
//        if (logout) {
//            //logout user
//        }
//        
        return "redirect:/client/dashboard";
    }

    @GetMapping("/comp-select")
    public String showCompanySelect() {
        return "client/company_search";
    }

    @PostMapping("/comp-select")
    public String showCompanySelect(@RequestParam int companyId, Model model) {
        model.addAttribute("comp_id", companyId); //comp_id will be used by company_date_pick
        return "client/company_date_pick";
    }

}
