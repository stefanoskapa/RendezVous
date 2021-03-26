package com.rendezvous.service;

import com.rendezvous.entity.Messages;
import com.rendezvous.model.JsonMessage;
import com.rendezvous.repository.MessagesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired 
    ConversationService conversationService;
    @Autowired
    UserService userService;
    
    public Optional<List<Messages>> findByConversationId(int conversationId) {
        return messagesRepository.findByConversationId(conversationId);

    }
    
    public void save(Messages message, JsonMessage msg) {
        messagesRepository.save(message);
        conversationService.notifyUsers(message.getConversationId(),msg);
       
    }
}
