
package com.rendezvous.model;

import java.time.LocalDateTime;


public class Message {

    String sender;
    String message;
    LocalDateTime timeStamp;

    public Message(String sender, String Message, LocalDateTime timeStamp) {
        this.sender = sender;
        this.message = Message;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Message{" + "sender=" + sender + ", message=" + message + ", timeStamp=" + timeStamp + '}';
    }
    
}
