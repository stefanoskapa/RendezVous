/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ChatController {

    @GetMapping("/chat")
    
    public String goToChatPage(Principal principal) {
        
        if (principal == null) {
            return "login";
        }
        
            return "chat";
        }
        
      
    

}
