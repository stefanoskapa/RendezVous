package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import com.rendezvous.service.ReCaptchaService;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ReCaptchaService reCaptchaRegisterService;

    @GetMapping("/client-register")
    public String showClientRegistration(@ModelAttribute("newClient") Client newClient, Model model) {
        return "client/register_client";
    }

    @PostMapping("/client-register")
    public String clientRegistration(@RequestParam(name = "g-recaptcha-response") String captchaResponse, @Valid @ModelAttribute("newClient") Client newClient, BindingResult bindingResult, Model model, RedirectAttributes attributes) {
        
        reCaptchaRegisterService.verify(captchaResponse);
        if (bindingResult.hasErrors()) {
            return "/client/register_client";
        }
        if (userRepository.findByEmail(newClient.getUser().getEmail()).isPresent()) {
            model.addAttribute("userExistsError", "This email is already being used!");
            model.addAttribute("newClient", new Client());
            return "client/register_client";
        }

        clientService.saveClient(newClient);

        attributes.addAttribute("registration", "true");
        return "redirect:/login";
    }

    @GetMapping("/company-register")
    public String showCompanyRegistration(@ModelAttribute("newCompany") Company newCompany, Model model) {
        return "company/register_company";
    }

    @PostMapping("/company-register")
    public String companyRegistration(@Valid @ModelAttribute("newCompany") Company newCompany, BindingResult bindingResult, Model model, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            return "company/register_company";
        }
        if (userRepository.findByEmail(newCompany.getUser().getEmail()).isPresent()) {
            model.addAttribute("userExistsError", "This email is already being used!");
            model.addAttribute("newCompany", new Company());
            return "company/register_company";
        }

        companyService.saveCompany(newCompany);
        attributes.addAttribute("registration", "true");
        return "redirect:/login";
    }

}
