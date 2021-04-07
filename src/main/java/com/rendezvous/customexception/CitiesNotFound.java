
package com.rendezvous.customexception;


public class CitiesNotFound extends Exception {
public CitiesNotFound(String errorMessage) {
        super(errorMessage);
    }
}
