package com.rendezvous.repository;

import com.rendezvous.entity.Role;
import com.rendezvous.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Stefanos
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}
