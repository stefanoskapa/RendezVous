
package com.rendezvous.service;

import com.rendezvous.entity.Conversation;
import com.rendezvous.model.JsonMessage;
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
    
    public void notifyUsers (int conversationId, JsonMessage message) {
        List<String> users = new LinkedList<>();
        Conversation conv = conversationRepository.findById(conversationId).get();
        users.add(conv.getClient().getUser().getEmail());
        users.add(conv.getCompany().getUser().getEmail());   
        dispatcher.notifyUsers(users, message);        
    }
    
}
