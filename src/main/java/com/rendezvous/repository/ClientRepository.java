package com.rendezvous.repository;

import com.rendezvous.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Stefanos
 */
public interface ClientRepository extends JpaRepository<Client,Integer> {
    
}
