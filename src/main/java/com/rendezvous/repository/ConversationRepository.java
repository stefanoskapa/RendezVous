package com.rendezvous.repository;

import com.rendezvous.entity.Conversation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    @Query(
            value = "SELECT * FROM conversation u WHERE (u.user1_id = ?1 || u.user2_id = ?1)",
            nativeQuery = true)
    List<Conversation> findByUserId(int userId);

    @Query(
            value = "SELECT * FROM conversation u WHERE (u.user1_id = ?1 && u.user2_id = ?2) ||(u.user1_id = ?2 && u.user2_id = ?1)",
            nativeQuery = true)
    Optional<Conversation> findConversation(int userId1, int userId2);

}
