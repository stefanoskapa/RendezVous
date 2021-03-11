/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PaymentController {
    
//    @Value("${STRIPE_PUBLIC_KEY}")
//    private String stripePublicKey;

//    @Autowired
//    private StripeService1 stripeService1;

    @GetMapping("/company/pro")
    public String showPremium(Model model) {
//        model.addAttribute("amount", 50 * 100); // In cents
//        model.addAttribute("stripePublicKey", stripePublicKey);
        return "company/premium/premium";
    }

    @PostMapping(value = "/company/pro/charge")
    public String chargeCard(HttpServletRequest request) throws Exception {
//        String token = request.getParameter("stripeToken");
//        Double amount = Double.parseDouble(request.getParameter("amount"));
//        stripeService1.chargeNewCard(token, amount);
//
//        System.out.println("Stripe auth succesfull");
        return "company/premium/success_premium";
    }
    
    @ExceptionHandler(com.stripe.exception.CardException.class)
    public String handleError() {
        System.out.println("Error during Stripe auth");
        return "company/premium/failed_premium";
    }
}
