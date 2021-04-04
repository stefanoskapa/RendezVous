
package com.rendezvous.customexception;


public class ConversationNotFound extends Exception {
    public ConversationNotFound(String errorMessage) {
        super(errorMessage);
    }
}
