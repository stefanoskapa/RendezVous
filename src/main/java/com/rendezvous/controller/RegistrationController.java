/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.entity.User;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.repository.RoleRepository;
import com.rendezvous.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/client-register")
    public String showClientRegistration(@ModelAttribute("newClient") Client newClient, Model model) {

        return "client/register_client";
    }

    @PostMapping("/client-register") //todo check if user email already exists
    public String clientRegistration(@Valid @ModelAttribute("newClient") Client newClient, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/client/register_client";
        }
        if (userRepository.findByEmail(newClient.getUser().getEmail()).isPresent()) {
            model.addAttribute("userExistsError", "This email is already being used!");
            model.addAttribute("newClient", new Client());
            return "client/register_client";
        }
        List<Role> userRole = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_CLIENT")) {
                userRole.add(a);
            }
        }
        newClient.getUser().setRoleList(userRole);
        String encodedPassword = bCryptPasswordEncoder.encode(newClient.getUser().getPassword());
        newClient.getUser().setPassword(encodedPassword);
        clientRepository.save(newClient);
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

        List<Role> userRole = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_COMPANY")) {
                userRole.add(a);
            }
        }
        newCompany.getUser().setRoleList(userRole);
        String encodedPassword = bCryptPasswordEncoder.encode(newCompany.getUser().getPassword());
        newCompany.getUser().setPassword(encodedPassword);
        companyRepository.save(newCompany);

        return "redirect:/login";

    }

}
