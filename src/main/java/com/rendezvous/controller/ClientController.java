package com.rendezvous.controller;

import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.CompCategory;
import com.rendezvous.entity.Company;
import com.rendezvous.service.CategoryService;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CategoryService categoryService;

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
    public String showDashboard(Model model) {
        model.addAttribute("listCategory", categoryService.getAllCategories());
        System.out.println(categoryService.getAllCategories());
        return "client/dashboard_client";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "client/profile_client";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, Model model) {
        Client loggedUser = (Client) model.getAttribute("client");

        if (bindingResult.hasErrors()) {
            return "client/profile_client";
        }

        client.setUser(loggedUser.getUser()); //making sure user havent malformed his credentials

        clientService.updateClient(client);

        return "redirect:/client/dashboard";
    }

    @GetMapping("/comp-select")
    public String showCompanySelect(Model model) {
        List<String> allCities = companyService.findAllCities();
        List<CompCategory> allCategories = categoryService.getAllCategories();
        Collections.sort(allCities);
        Collections.sort(allCategories, Comparator.comparing(CompCategory::getCategory));
        model.addAttribute("listCategory", allCategories);
        model.addAttribute("listCities", allCities);
        return "client/company_search";
    }

    @GetMapping("/date-select")
    public String showDateSelect(@RequestParam int companyId, Model model) {

        try {
            Company tempCompany = companyService.findCompanyById(companyId);
            model.addAttribute("comp_id", tempCompany.getId());
            model.addAttribute("comp_name", tempCompany.getDisplayName());
            model.addAttribute("fname", tempCompany.getFname());
            model.addAttribute("lname", tempCompany.getLname());
            model.addAttribute("comp_email", tempCompany.getUser().getEmail());
        } catch (CompanyIdNotFound ex) {
            return "client/company_search";
        }
        return "client/company_date_pick";
    }

}
