package com.rendezvous.repository;

import com.rendezvous.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Stefanos
 */
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    
}