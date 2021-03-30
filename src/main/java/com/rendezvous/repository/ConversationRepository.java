package com.rendezvous.repository;

import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Stefanos
 */
public interface ConversationRepository extends JpaRepository <Conversation, Integer>{
    Conversation findByClientIdAndCompanyId(int clientId, int companyId);
    List<Conversation> findByClientId(int clientId);
    List<Conversation> findByCompanyId(int companyId);
}
