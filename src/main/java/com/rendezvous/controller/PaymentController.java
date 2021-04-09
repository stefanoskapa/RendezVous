package com.rendezvous.controller;

import com.rendezvous.entity.Company;
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

    private final double ammount = 19.99; //19.99 currency(EUR)

    @GetMapping("/pro")
    public String showPremium(Model model, Principal principal) {
        model.addAttribute("amount", ammount * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            model.addAttribute("company_name", c.getDisplayName());
            model.addAttribute("company", c);
        }

        return "company/premium/premium";
    }

//    //for testing success purchase page
//    @GetMapping(value = "/pro/cuss")
//    public String fou(Principal principal, Model model) {
//
//        if (principal != null) {
//            Company c = companyService.findCompanyByEmail(principal.getName());
//            model.addAttribute("company_name", c.getDisplayName());
//            model.addAttribute("company", c);
//        }
//        return "company/premium/success_premium";
//    }
//
//    //for testing failed purchase page
//    @GetMapping(value = "/pro/fail")
//    public String fou2(Principal principal, Model model) {
//        if (principal != null) {
//            Company c = companyService.findCompanyByEmail(principal.getName());
//            model.addAttribute("company_name", c.getDisplayName());
//            model.addAttribute("company", c);
//        }
//        return "company/premium/failed_premium";
//    }

    @PostMapping(value = "/pro/charge")
    public String chargeCard(HttpServletRequest request, Principal principal, Model model) throws Exception {
        String token = request.getParameter("stripeToken");

        //if charging fails then stripe.exception.CardException will be thrown
        stripeService.chargeNewCard(token, ammount);

        //if no exception occurs then accept payment as successfull and save to db
        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            companyService.setPremiumStatus(c);
            model.addAttribute("company_name", c.getDisplayName());
            model.addAttribute("company", c);
        }

        return "company/premium/success_premium";
    }

    @ExceptionHandler(com.stripe.exception.CardException.class)
    public String handleError(Principal principal, Model model) {
        if (principal != null) {
            Company c = companyService.findCompanyByEmail(principal.getName());
            model.addAttribute("company_name", c.getDisplayName());
            model.addAttribute("company", c);
        }
        return "company/premium/failed_premium";
    }

}
