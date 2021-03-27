
package com.rendezvous.service;

import com.rendezvous.entity.Conversation;
import com.rendezvous.repository.ConversationRepository;
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
