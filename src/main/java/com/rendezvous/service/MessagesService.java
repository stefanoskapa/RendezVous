package com.rendezvous.service;

import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
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

    public Optional<List<Messages>> findByConversationId(int conversationId) {
        return messagesRepository.findByConversationId(conversationId);

    }

    public void save(Messages message) {

        messagesRepository.save(message);
    }
}
