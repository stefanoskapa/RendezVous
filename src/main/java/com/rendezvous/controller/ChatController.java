/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.customexception.ClientIdNotFound;
import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.customexception.ConversationNotFound;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.entity.User;
import com.rendezvous.model.Message;
import com.rendezvous.model.UserProps;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import com.rendezvous.service.ConversationService;
import com.rendezvous.service.MessagesService;
import com.rendezvous.service.UserService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    

    @MessageMapping("/secured/room") //send incoming message to the right user
    public void sendSpecific(
            @Payload Message msg,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        msg.setFrom(user.getName());
        simpMessagingTemplate.convertAndSendToUser(
                msg.getTo(), "/secured/user/queue/specific-user", msg);
        User tempUser = userService.findByEmail(user.getName()); // sender
        User tempUser2 = userService.findByEmail(msg.getTo()); // recipient
        Optional<Conversation> conv = conversationService.findConversation(tempUser.getId(), tempUser2.getId());
        if (!conv.isPresent()) throw new ConversationNotFound("Conversation between userID=" + tempUser.getId()+ " iserID="+tempUser2.getId()+" not found!");
        Messages tempMessage = new Messages(tempUser.getId(), conv.get().getId(),msg.getText(),LocalDateTime.now());
        messagesService.save(tempMessage, conv.get());
    }

    @GetMapping("/conv") //returns list of conversation-partners
    public ResponseEntity<List<UserProps>> fetchConvPartners(Principal principal) throws ClientIdNotFound, CompanyIdNotFound{
        User tempUser = userService.findByEmail(principal.getName());
        List<Conversation> conv = conversationService.findByUserId(tempUser.getId());
        List<UserProps> convPartners = new LinkedList<>();
        for (Conversation a : conv) {
            UserProps up;
            if (tempUser.getRoleList().get(0).getRole().equals("ROLE_COMPANY")) {
                Client tempClient = clientService.findClientByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(tempClient.getFname(),tempClient.getLname(),tempClient.getUser().getEmail());               
            } else {
                Company comp = companyService.findCompanyByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(comp.getFname(),comp.getLname(), comp.getUser().getEmail(),comp.getDisplayName());               
            }
            convPartners.add(up);
        }
        return new ResponseEntity<>(convPartners, HttpStatus.OK);
    }
    
 
    @GetMapping("/myprops") // send user information to himself
    public ResponseEntity<UserProps> fetchMyData(Principal principal) throws ClientIdNotFound, CompanyIdNotFound{
        User tempUser = userService.findByEmail(principal.getName());
        UserProps up; 
        if (tempUser.getRoleList().get(0).getRole().equals("ROLE_CLIENT")) {
            Client tempClient = clientService.findClientByUserEmail(principal.getName());
            System.out.println("client found:" + tempClient.toString());
            up = new UserProps(tempClient.getFname(),tempClient.getLname(),tempClient.getUser().getEmail());
        } else {
            Company comp = companyService.findCompanyByUserEmail(principal.getName());
            up = new UserProps(comp.getFname(),comp.getLname(),comp.getUser().getEmail(),comp.getDisplayName());
        }
        return new ResponseEntity<>(up, HttpStatus.OK);
    }

    @GetMapping("/load/{userEmail}") // loads messages with particular conversation-partner
    public ResponseEntity<List<Message>> fetchMessages(@PathVariable String userEmail, Principal principal) throws ConversationNotFound{
        User tempUser = userService.findByEmail(principal.getName());
        User otherUser = userService.findByEmail(userEmail);
        Optional <Conversation> tempConv = conversationService.findConversation(tempUser.getId(), otherUser.getId());
        List<Message> msgsToSend = new LinkedList<>();
        if (tempConv.isPresent()) {
            List<Messages> msgs = messagesService.findByConversationId(tempConv.get().getId()).get();
            for (Messages a : msgs) {
                String sender = tempUser.getId() == a.getUserId()? principal.getName() : userEmail;
                Message om = new Message(sender, a.getMessage(), a.getTimestamp().toString());
                msgsToSend.add(om);
            }
        } else {
            conversationService.save(new Conversation(tempUser,otherUser));
        }
        return new ResponseEntity<>(msgsToSend, HttpStatus.OK);
    }

}
