
package com.rendezvous.customexception;


public class ClientIdNotFound extends Exception{

    public ClientIdNotFound(String errorMessage) {
        super(errorMessage);
    }
}

