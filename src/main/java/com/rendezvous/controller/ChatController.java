package com.rendezvous.controller;

import com.rendezvous.customexception.ConversationNotFound;
import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.entity.User;
import com.rendezvous.model.Message;
import com.rendezvous.service.ConversationService;
import com.rendezvous.service.MessagesService;
import com.rendezvous.service.UserService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ConversationService conversationService;

    @MessageMapping("/secured/room") //send incoming message to the right user
    public void sendSpecific(
            @Payload Message msg,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        msg.setFrom(user.getName());
        simpMessagingTemplate.convertAndSendToUser(
                msg.getTo(), "/secured/user/queue/specific-user", msg);
        User tempUser = userService.findByEmail(user.getName()); // sender
        User tempUser2 = userService.findByEmail(msg.getTo()); // recipient
        Optional<Conversation> conv = conversationService.findConversation(tempUser.getId(), tempUser2.getId());
        if (!conv.isPresent()) {
            throw new ConversationNotFound("Conversation between userID=" + tempUser.getId() + " iserID=" + tempUser2.getId() + " not found!");
        }
        Messages tempMessage = new Messages(tempUser.getId(), conv.get().getId(), msg.getText(), LocalDateTime.now());
        messagesService.save(tempMessage);
    }

}
