package com.rendezvous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/client")
    public String client() {
        return "redirect:/client/dashboard";
    }

    @GetMapping("/company")
    public String company() {
        return "redirect:/company/dashboard";
    }
}
