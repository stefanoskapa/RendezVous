/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.entity.User;
import com.rendezvous.model.Message;
import com.rendezvous.model.UserProps;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.repository.ConversationRepository;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.MessagesService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @MessageMapping("/secured/room") //send incoming message to the right user
    public void sendSpecific(
            @Payload Message msg,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        msg.setFrom(user.getName());
        simpMessagingTemplate.convertAndSendToUser(
                msg.getTo(), "/secured/user/queue/specific-user", msg);
        User tempUser = userRepository.findByEmail(user.getName()).get(); // sender
        User tempUser2 = userRepository.findByEmail(msg.getTo()).get(); // recipient
        Conversation conv = conversationRepository.findConversation(tempUser.getId(), tempUser2.getId());
        Messages tempMessage = new Messages(tempUser.getId(), conv.getId(),msg.getText(),LocalDateTime.now());
        messagesService.save(tempMessage, conv);
    }

    @GetMapping("/conv") //returns list of conversation-partners
    public ResponseEntity<List<UserProps>> fetchConvPartners(Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        List<Conversation> conv = conversationRepository.findByUserId(tempUser.getId());
        List<UserProps> convPartners = new LinkedList<>();
        for (Conversation a : conv) {
            UserProps up;
            if (tempUser.getRoleList().get(0).getRole().equals("ROLE_COMPANY")) {
                Client tempClient = clientRepository.findClientByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(tempClient.getFname(),tempClient.getLname(),tempClient.getUser().getEmail());               
            } else {
                Company comp = companyRepository.findCompanyByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(comp.getFname(),comp.getLname(), comp.getUser().getEmail(),comp.getDisplayName());               
            }
            convPartners.add(up);
        }
        return new ResponseEntity<>(convPartners, HttpStatus.OK);
    }

    @GetMapping("/myprops") // send user information to himself
    public ResponseEntity<UserProps> fetchMyData(Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        UserProps up; 
        if (tempUser.getRoleList().get(0).getRole().equals("ROLE_CLIENT")) {
            Client tempClient = clientRepository.findClientByUserEmail(principal.getName()).get();
            up = new UserProps(tempClient.getFname(),tempClient.getLname(),tempClient.getUser().getEmail());
        } else {
            Company comp = companyRepository.findCompanyByUserEmail(principal.getName()).get();
            up = new UserProps(comp.getFname(),comp.getLname(),comp.getUser().getEmail(),comp.getDisplayName());
        }
        return new ResponseEntity<>(up, HttpStatus.OK);
    }

    @GetMapping("/load/{userEmail}") // loads messages with particular conversation-partner
    public ResponseEntity<List<Message>> fetchMessages(@PathVariable String userEmail, Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        User otherUser = userRepository.findByEmail(userEmail).get();
        Conversation tempConv = conversationRepository.findConversation(tempUser.getId(), otherUser.getId());
        List<Message> msgsToSend = new LinkedList<>();
        if (tempConv != null) {
            List<Messages> msgs = messagesService.findByConversationId(tempConv.getId()).get();
            for (Messages a : msgs) {
                String sender = tempUser.getId() == a.getUserId()? principal.getName() : userEmail;
                Message om = new Message(sender, a.getMessage(), a.getTimestamp().toString());
                msgsToSend.add(om);
            }
        } else {
            conversationRepository.save(new Conversation(tempUser,otherUser));
        }
        return new ResponseEntity<>(msgsToSend, HttpStatus.OK);
    }

}
