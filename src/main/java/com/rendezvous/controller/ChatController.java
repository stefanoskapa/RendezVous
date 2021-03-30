/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.repository.ConversationRepository;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.MessagesService;
import com.rendezvous.service.NotificationDispatcher;
import com.rendezvous.util.Conversion;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @Autowired
    private UserRepository userRepository;
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
}
