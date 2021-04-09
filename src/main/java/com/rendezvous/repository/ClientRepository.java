package com.rendezvous.repository;

import com.rendezvous.entity.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findClientByUserEmail(String email);

    Optional<Client> findClientByUserId(int id);

    Optional<Client> findById(int id);

}
