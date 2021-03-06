
package com.rendezvous;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
APIs than can verify if the authentication succeeded
*/

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome</h1>";
    }
    
    @GetMapping("/client")
    public String user() {
        return "<h1>Welcome Client</h1>";
    }
    
    @GetMapping("/company")
    public String company() {
        return "<h1>Welcome Company</h1>";
    }
    
}
