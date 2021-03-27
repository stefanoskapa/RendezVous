/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Company;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.service.CompanyService;
import com.rendezvous.service.StripeService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/company")
public class PaymentController {
    
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private StripeService stripeService;
    @Autowired
    private CompanyService companyService;
    
    private double ammount=50; //50 currency(EUR)

    @GetMapping("/pro")
    public String showPremium(Model model,Principal principal) {
        model.addAttribute("amount", ammount * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);

        return "company/premium/premium";
    }

    @PostMapping(value = "/pro/charge")
    public String chargeCard(HttpServletRequest request, Principal principal) throws Exception {
        String token = request.getParameter("stripeToken");
        
        //if charging fails then stripe.exception.CardException will be thrown
        stripeService.chargeNewCard(token, ammount);

        //if no exception occurs then accept payment as successfull and save to db
        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            companyService.setPremiumStatus(c);
        }
        
        return "company/premium/success_premium";
    }
    
    @ExceptionHandler(com.stripe.exception.CardException.class)
    public String handleError() {
        return "company/premium/failed_premium";
    }
    
}
