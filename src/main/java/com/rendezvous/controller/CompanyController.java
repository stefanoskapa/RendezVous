package com.rendezvous.controller;

import com.rendezvous.customexception.IncorrectWorkingHours;
import com.rendezvous.entity.Company;
import com.rendezvous.model.WorkWeek;
import com.rendezvous.service.CategoryService;
import com.rendezvous.service.CompanyService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public void addAttributes(Principal principal, Model model) {

        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            model.addAttribute("company_name", c.getDisplayName());
            model.addAttribute("company", c);
        }
    }

    @GetMapping("/")
    public String redirectToDashboard() {
        return "redirect:/company/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "company/dashboard_company";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "company/profile_company";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult, Model model) {
        Company loggedUser = (Company) model.getAttribute("company");

        if (bindingResult.hasErrors()) {
            return "company/profile_company";
        }

        company.setUser(loggedUser.getUser()); //making sure user havent malformed his credentials
        company.setAfm(loggedUser.getAfm());    //making sure user havent malformed his credentials
        company.setPremium(loggedUser.getPremium());    //making sure user havent malformed his credentials

        companyService.updateCompany(company);
        return "redirect:/company/dashboard";
    }

    @GetMapping("/business-hours")
    public String showBusinessHours(Principal principal, Model model) {
        Company company = (Company) model.getAttribute("company");
        WorkWeek workWeek = companyService.findWorkingHoursByCompany(company);
        model.addAttribute("weekHours", workWeek);
        return "company/business_hours";
    }

    @PostMapping("/business-hours")
    public String updateBusinessHours(@Valid @ModelAttribute("weekHours") WorkWeek workWeek, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "company/business_hours";
        }

        Company company = (Company) model.getAttribute("company");

        try {
            companyService.saveWorkingHours(company, workWeek);
        } catch (IncorrectWorkingHours ex) {
            redirectAttributes.addFlashAttribute("IncorrectWorkingHours", ex.getMessage());
            return "redirect:/company/business-hours";
        }

        return "redirect:/company/dashboard";
    }

}
