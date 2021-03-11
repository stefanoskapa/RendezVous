/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.dto.ClientUser;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Role;
import com.rendezvous.entity.User;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.RoleRepository;
import com.rendezvous.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/client-register")
    public String showClientRegistration(@ModelAttribute("newClient") Client newClient,Model model) {
        
        return "client/register_client";
    }

    @PostMapping("/client-register")
    public String clientRegistration(@ModelAttribute("newClient") Client newClient, Model m) {
        System.out.println(newClient.getUserId().getEmail());
        
        List<Role> userRole = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_CLIENT")) {
               userRole.add(a);
            }
        }
        newClient.getUserId().setRoleList(userRole);
        
        //create user
        User newUser = new User();
        newUser.setEmail(newClient.getUserId().getEmail());
        newUser.setPassword(newClient.getUserId().getPassword());
        newUser.setRoleList(userRole);
        userRepository.save(newUser); 
        
        //create client
        Client client = new Client();
        client.setFname(newClient.getFname());
        client.setLname(newClient.getLname());
        client.setTel(newClient.getTel());
        client.setUserId(newUser);
        clientRepository.save(client);
        
        
        
        
        return "redirect:/login";
    }

    @GetMapping("/company-register")
    public String showCompanyRegistration() {
        return "register_company";
    }

    @PostMapping("/company-register")
    public String companyRegistration(RedirectAttributes redirectAttributes) {
        //todo
        //meta apo epitixi prosthiki xristi:

        redirectAttributes.addAttribute("success_creation", "Your account has been succesfully created");

        return "redirect:/login";
    }

}
