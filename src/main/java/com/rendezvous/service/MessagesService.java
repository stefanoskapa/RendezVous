package com.rendezvous.service;

import com.rendezvous.entity.Messages;
import com.rendezvous.model.Message;
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
    
    public void save(Messages message, Message msg) {
        messagesRepository.save(message);
        System.out.println("WE GOT A NEW MESSAGE! ");
        System.out.println("In the conversation "+ message.getConversationId());
        System.out.println("Check if client or company are connected and notify them");
       // if (userService.findById(message.getUserId())
        conversationService.notifyUsers(message.getConversationId(),msg);
       
    }
}
