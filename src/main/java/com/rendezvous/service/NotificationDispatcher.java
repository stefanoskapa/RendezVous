package com.rendezvous.service;

import com.rendezvous.entity.Messages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Service
public class NotificationDispatcher {
    
    @Autowired
    private SimpMessagingTemplate template;
    private final Map<String, String> userAndSession = new HashMap<>();

    public void add(String user, String sessionId) {
        userAndSession.put(user, sessionId);
    }

    public void remove(String sessionId) {
        userAndSession.remove(sessionId);
    }

    public void notifyUsers(List<String> users, Messages message) {
        for (String user : users) {
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(userAndSession.get(user));
            headerAccessor.setLeaveMutable(true);
            if (userAndSession.get(user) != null) {
                template.convertAndSendToUser(
                        userAndSession.get(user),
                        "/topic/messages",message,
                        headerAccessor.getMessageHeaders());
            }
        }
    }

    @EventListener
    public void sessionDisconnectionHandler(SessionDisconnectEvent event) {
        String sessionId = event.getUser().getName();
        remove(sessionId);
    }
}
