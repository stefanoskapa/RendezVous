/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.customexception.ClientIdNotFound;
import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.entity.User;
import com.rendezvous.repository.ConversationRepository;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import com.rendezvous.service.ConversationService;
import com.rendezvous.service.MessagesService;
import com.rendezvous.service.NotificationDispatcher;
import com.rendezvous.service.UserService;
import com.rendezvous.util.Conversion;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessagesService messagesService;

    private final NotificationDispatcher dispatcher;

    @Autowired
    public ChatController(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @MessageMapping("/start")
    public void start(StompHeaderAccessor stompHeaderAccessor) {
        dispatcher.add(stompHeaderAccessor.getUser().getName(), stompHeaderAccessor.getSessionId());
    }

    @MessageMapping("/client/history")
    public void addClientMessageToHistory(Messages message) {
        Conversation conv = conversationRepository.findById(message.getConversationId()).get();
        message.setTimestamp(Conversion.adjustTime(message.getTimestamp()));
        messagesService.save(message, conv);
    }

    @MessageMapping("/company/history")
    public void addCompanyMessageToHistory(Messages message) {
        Conversation conv = conversationRepository.findById(message.getConversationId()).get();
        message.setTimestamp(Conversion.adjustTime(message.getTimestamp()));
        messagesService.save(message, conv);
    }

    @GetMapping("/chatnow/{partnerId}") //redirect client or company to the right controller
    public String goToChatPage(@PathVariable int partnerId, Principal principal,
            Model model
    ) {

        if (principal != null) {
            User tempUser = userRepository.findByEmail(principal.getName()).get();
            if (tempUser.getRoleList().get(0).getRole().equals("ROLE_COMPANY")) {
                return "redirect:/chatnow/client/" + partnerId;
            } else {
                return "redirect:/chatnow/comp/" + partnerId;
            }
        }
        return "login";
    }

    @GetMapping("/chatnow/comp/{company_id}") //user is a client and wants to chat with company
    public String chatWithCompany(@PathVariable int company_id, Model model,
            Principal principal) throws CompanyIdNotFound {
        Client client = clientService.findClientByEmail(principal.getName());
        Company company = companyService.findCompanyById(company_id);
        Conversation conv = conversationRepository.findByClientIdAndCompanyId(client.getId(), company_id);
        model.addAttribute("me", client.getFname() + "-" + client.getLname());
        model.addAttribute("you", company.getFname() + "-" + company.getLname());
        model.addAttribute("role", "client");
        model.addAttribute("id", company_id);
        model.addAttribute("myuid", client.getUser().getId());
        model.addAttribute("hisuid", company.getUser().getId());
        model.addAttribute("convId", conv.getId());
        return "chat";
    }

    @GetMapping("/chatnow/client/{client_id}") //user is a company and wants to chat with client
    public String chatWithClient(@PathVariable int client_id, Model model,
            Principal principal) throws ClientIdNotFound {
        Company company = companyService.findCompanyByEmail(principal.getName());
        Client client = clientService.findClientById(client_id);
        Conversation conv = conversationRepository.findByClientIdAndCompanyId(client_id, company.getId());
        model.addAttribute("you", client.getFname() + "-" + client.getLname());
        model.addAttribute("me", company.getFname() + "-" + company.getLname());
        model.addAttribute("role", "company");
        model.addAttribute("id", client_id);
        model.addAttribute("myuid", company.getUser().getId());
        model.addAttribute("hisuid", client.getUser().getId());
        model.addAttribute("convId", conv.getId());
        return "chat";
    }

}
