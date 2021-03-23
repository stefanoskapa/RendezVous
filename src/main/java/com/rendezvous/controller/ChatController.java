/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;
import com.rendezvous.entity.User;
import com.rendezvous.repository.UserRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

   // @Autowired
    //private ClientService clientService;
    //@Autowired
    //private CompanyService companyService;
    @Autowired
    private UserRepository userRepository;
    //@Autowired 
    //private ConversationRepository conversationRepository;
    //@Autowired 
    //private MessagesRepository messagesRepository;

    @GetMapping("/chatnow/{partnerId}")
    public String goToChatPage(@PathVariable int partnerId, Principal principal) {

        if (principal != null) {

            User tempUser = userRepository.findByEmail(principal.getName()).get();

            if (tempUser.getRoleList().get(0).equals("ROLE_COMPANY")) {
               return "redirect:/chatnow/client/" + partnerId;
            } else {
               return "redirect:/chatnow/comp/" + partnerId;
            }
        }

        return "login";
    }
    
    @GetMapping("/chatnow/comp/{company_id}") //user is a client and wants to chat with company
    public String chatWithCompany(@PathVariable int company_id, Model model) {
        
        //TODO need to get comp_id in chat.jsp
        //TODO error handling id not found
        model.addAttribute("comp_id", company_id);
        return "chat";
    }

    @GetMapping("/chatnow/client/{client_id}") //user is a company and wants to chat with client
    public String chatWithClient(@PathVariable int client_id, Model model) {
        //TODO need to get comp_id in chat.jsp
        //TODO error handling id not found
        return "chat";
    }

}
