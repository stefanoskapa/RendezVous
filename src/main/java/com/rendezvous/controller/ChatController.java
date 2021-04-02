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
import com.rendezvous.service.NotificationDispatcher;
import com.rendezvous.util.Conversion;
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
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
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
    private NotificationDispatcher dispatcher;
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
        User tempUser = userRepository.findByEmail(user.getName()).get();
        User tempUser2 = userRepository.findByEmail(msg.getTo()).get();
        Conversation conv = conversationRepository.findConversation(tempUser.getId(), tempUser2.getId());
        Messages tempMessage = new Messages(tempUser.getId(), conv.getId(),msg.getText(),LocalDateTime.now());
        messagesService.save(tempMessage, conv);
    }

    @MessageMapping("/start")
    public void start(StompHeaderAccessor stompHeaderAccessor) {
        dispatcher.add(stompHeaderAccessor.getUser().getName(), stompHeaderAccessor.getSessionId());
    }

    @MessageMapping("/client/history")
    public void addClientMessageToHistory(Messages message, Principal principal) {
        if (userRepository.findById(message.getUserId()).getEmail().equals(principal.getName())) {
            Conversation conv = conversationRepository.findById(message.getConversationId()).get();
            message.setTimestamp(Conversion.adjustTime(message.getTimestamp()));
            messagesService.save(message, conv);
        }
    }

    @MessageMapping("/company/history")
    public void addCompanyMessageToHistory(Messages message, Principal principal) {
        if (userRepository.findById(message.getUserId()).getEmail().equals(principal.getName())) {
            Conversation conv = conversationRepository.findById(message.getConversationId()).get();
            message.setTimestamp(Conversion.adjustTime(message.getTimestamp()));
            messagesService.save(message, conv);
        }
    }

    @GetMapping("/conv") //has to return a list of full UserProps
    public ResponseEntity<List<UserProps>> fetchConvPartners(Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        List<Conversation> conv = conversationRepository.findByUserId(tempUser.getId());
        List<UserProps> convPartners = new LinkedList<>();
        for (Conversation a : conv) {
            boolean is_comp = tempUser.getRoleList().get(0).getRole().equals("ROLE_COMPANY");
            if (is_comp) {
                Client tempClient = clientRepository.findClientByUserId(a.getPartnerId(tempUser.getId()).getId());
                UserProps up = new UserProps();
                up.setFname(tempClient.getFname());
                up.setLname(tempClient.getLname());
                up.setEmail(tempClient.getUser().getEmail());
                convPartners.add(up);
            } else {
                Company tempCompany = companyRepository.findCompanyByUserId(a.getPartnerId(tempUser.getId()).getId());
                UserProps up = new UserProps();
                up.setFname(tempCompany.getFname());
                up.setLname(tempCompany.getLname());
                up.setEmail(tempCompany.getUser().getEmail());
                up.setCompanyName(tempCompany.getDisplayName());
                convPartners.add(up);
            }
        }
        return new ResponseEntity<>(convPartners, HttpStatus.OK);
    }

    @GetMapping("/myprops")
    public ResponseEntity<UserProps> fetchMyData(Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        UserProps up = null;
        if (tempUser.getRoleList().get(0).getRole().equals("ROLE_CLIENT")) {
            Client tempClient = clientRepository.findClientByUserEmail(principal.getName()).get();
            up = new UserProps();
            up.setFname(tempClient.getFname());
            up.setLname(tempClient.getLname());
            up.setEmail(tempClient.getUser().getEmail());
        } else {
            Company tempCompany = companyRepository.findCompanyByUserEmail(principal.getName()).get();
            up = new UserProps();
            up.setFname(tempCompany.getFname());
            up.setLname(tempCompany.getLname());
            up.setEmail(tempCompany.getUser().getEmail());
            up.setCompanyName(tempCompany.getDisplayName());
        }
        return new ResponseEntity<>(up, HttpStatus.OK);
    }

    @GetMapping("/load/{userEmail}")
    public ResponseEntity<List<Message>> fetchMessages(@PathVariable String userEmail, Principal principal) {
        User tempUser = userRepository.findByEmail(principal.getName()).get();
        User otherUser = userRepository.findByEmail(userEmail).get();
        Conversation tempConv = conversationRepository.findConversation(tempUser.getId(), otherUser.getId());
        List<Message> msgsToSend = new LinkedList<>();
        if (tempConv != null) {
            List<Messages> msgs = messagesService.findByConversationId(tempConv.getId()).get();
            for (Messages a : msgs) {
                Message om = null;
                if (tempUser.getId() == a.getUserId()) {
                    om = new Message(principal.getName(), a.getMessage(), a.getTimestamp().toString());
                } else {
                    om = new Message(userEmail, a.getMessage(), a.getTimestamp().toString());
                }
                msgsToSend.add(om);
            }
        } else {
            tempConv = new Conversation();
            tempConv.setUser1Id(tempUser);
            tempConv.setUser2Id(otherUser);
            conversationRepository.save(tempConv);
        }
        return new ResponseEntity<>(msgsToSend, HttpStatus.OK);
    }

}
