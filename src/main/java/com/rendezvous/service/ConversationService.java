
package com.rendezvous.service;

import com.rendezvous.entity.Conversation;
import com.rendezvous.model.Message;
import com.rendezvous.repository.ConversationRepository;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

        
public class ConversationService {
    
    @Autowired
    ConversationRepository conversationRepository;
    
    private final NotificationDispatcher dispatcher;

    @Autowired
    public ConversationService(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public void notifyUsers (int conversationId, Message message) {
        System.out.println("Inside ConversationService.notifyUsers()");
        List<String> users = new LinkedList<>();
        Conversation conv = conversationRepository.findById(conversationId).get();
        String clientEmail = conv.getClient().getUser().getEmail();
        String companyEmail = conv.getCompany().getUser().getEmail();
        users.add(clientEmail);
        users.add(companyEmail);   
        System.out.println("Clientemail: " + clientEmail + ", companyEmail: " + companyEmail);
        System.out.println("Just before dispatcher.notifyUsers");
        dispatcher.notifyUsers(users, message);
        
    }
    
}
