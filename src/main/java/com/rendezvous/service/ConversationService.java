
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
    
    
    
    public Conversation findById(int convId) {
        return conversationRepository.findById(convId).get();
    }
    
   
}
