package com.rendezvous.model;

public class Message {

    private String from;
    private String to;
    private String text;
    private String timestamp;

    public Message(String from, String text, String timestamp) {
        this.from = from;
        this.text = text;
        this.timestamp = timestamp;
    }

    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    

    @Override
    public String toString() {
        return "Message{" + "from=" + from + ", to=" + to + ", text=" + text + '}';
    }
    
    
}
