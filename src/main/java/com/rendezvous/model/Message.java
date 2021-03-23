
package com.rendezvous.model;

import java.time.LocalDateTime;


public class Message {

    String sender;
    String Message;
    LocalDateTime timeStamp;

    public Message(String sender, String Message, LocalDateTime timeStamp) {
        this.sender = sender;
        this.Message = Message;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    
}
