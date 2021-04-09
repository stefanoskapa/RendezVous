package com.rendezvous.repository;

import com.rendezvous.entity.Messages;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer> {

    Optional<List<Messages>> findByConversationId(int conversationId);

}
