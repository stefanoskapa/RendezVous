package com.rendezvous.repository;

import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Stefanos
 */
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    //Conversation findByUser1IdAndUser2Id(int user1, int user2);

    @Query(
            value = "SELECT * FROM conversation u WHERE (u.user1_id = ?1 || u.user2_id = ?1)",
            nativeQuery = true)
    List<Conversation> findByUserId(int userId); 
    
    @Query(
            value = "SELECT * FROM conversation u WHERE (u.user1_id = ?1 && u.user2_id = ?2) ||(u.user1_id = ?2 && u.user2_id = ?1)",
            nativeQuery = true)
    Conversation findConversation(int userId1, int userId2);
    
    //Conversation findByUserId1EmailAndUserId2EmailOrUserId2EmailAndUserId1Email(String email1, String email2);
}
