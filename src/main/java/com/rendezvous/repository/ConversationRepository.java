package com.rendezvous.repository;

import com.rendezvous.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Stefanos
 */
public interface ConversationRepository extends JpaRepository <Conversation, Integer>{
    Conversation findByClientIdAndCompanyId(int clientId, int companyId);
}
