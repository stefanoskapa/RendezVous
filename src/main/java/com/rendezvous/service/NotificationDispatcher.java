package com.rendezvous.service;

import com.rendezvous.model.Message;
import com.rendezvous.model.Notification;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.websocket.Session;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
@Service
public class NotificationDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationDispatcher.class);

    private final SimpMessagingTemplate template;

    //private Set<String> listeners = new HashSet<>();
    private Map<String,String> userAndSession = new HashMap<>();
    public NotificationDispatcher(SimpMessagingTemplate template) {
        this.template = template;
    }

   
    

    public void add(String user,String sessionId) {
        userAndSession.put(user, sessionId);
    }

    public void remove(String sessionId) {
        userAndSession.remove(sessionId);
       // listeners.remove(sessionId);
    }
/*
    @Scheduled(fixedDelay = 2000)
    public void dispatch() {
        for (String listener : listeners) {
            LOGGER.info("Sending notification to " + listener);
            
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(listener);
            headerAccessor.setLeaveMutable(true);

            int value = (int) Math.round(Math.random() * 100d);
            System.out.println("Listener is " + listener);
            template.convertAndSendToUser(
                    listener,
                    "/topic/thing",
                    new Notification(Integer.toString(value)),
                    headerAccessor.getMessageHeaders());
        }
    } */
    
    public void notifyUsers(List<String> users, Message message) {
        for (String user : users) {
            LOGGER.info("Sending notification to " + user);

            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
            headerAccessor.setSessionId(userAndSession.get(user));
            headerAccessor.setLeaveMutable(true);

           // int value = (int) Math.round(Math.random() * 100d);
           if (userAndSession.get(user) != null) {
            template.convertAndSendToUser(
                    userAndSession.get(user),
                    "/topic/thing",
                    //new Notification(Integer.toString(666999)),
                    message,
                    headerAccessor.getMessageHeaders());
           }
        }
    }

    @EventListener
    public void sessionDisconnectionHandler(SessionDisconnectEvent event) {
        String sessionId = event.getUser().getName();
        LOGGER.info("Disconnecting " + sessionId + "!");
        remove(sessionId);
    }
}
