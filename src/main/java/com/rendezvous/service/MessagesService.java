package com.rendezvous.service;

import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.model.JsonMessage;
import com.rendezvous.repository.MessagesRepository;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired 
    private ConversationService conversationService;
    private final NotificationDispatcher dispatcher;

    @Autowired
    public MessagesService(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public Optional<List<Messages>> findByConversationId(int conversationId) {
        return messagesRepository.findByConversationId(conversationId);

    }
    
    public void save(Messages message, Conversation conv) {
        List<String> users = new LinkedList<>();
        users.add(conv.getClient().getUser().getEmail());
        users.add(conv.getCompany().getUser().getEmail());   
        dispatcher.notifyUsers(users, message);        
        messagesRepository.save(message);     
    }
}
