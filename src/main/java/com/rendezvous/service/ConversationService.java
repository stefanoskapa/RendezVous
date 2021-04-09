package com.rendezvous.service;

import com.rendezvous.customexception.ConversationNotFound;
import com.rendezvous.entity.Conversation;
import com.rendezvous.repository.ConversationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation findById(Integer convId) throws ConversationNotFound {
        Optional<Conversation> conv = conversationRepository.findById(convId);
        conv.orElseThrow(() -> new ConversationNotFound("Conversation " + convId + " not found!"));
        return conv.get();
    }

    public Optional<Conversation> findConversation(int userId1, int userId2) {
        Optional<Conversation> conv = conversationRepository.findConversation(userId1, userId2);
        return conv;
    }

    public List<Conversation> findByUserId(int userId) {
        return conversationRepository.findByUserId(userId);
    }

    public void save(Conversation conv) {
        conversationRepository.save(conv);
    }

}
