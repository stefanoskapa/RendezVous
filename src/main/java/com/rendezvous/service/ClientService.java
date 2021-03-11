/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.service;

import com.rendezvous.entity.Client;
import com.rendezvous.repository.ClientRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientRepository clientRepository;
    
    public Client findClientByEmail(String email){
        Optional<Client> client = clientRepository.findClientByUserIdEmail(email);
        client.orElseThrow(()-> new UsernameNotFoundException("User " + email + " not found!"));
        return client.get();
    }
}
