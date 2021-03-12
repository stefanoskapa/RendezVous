package com.rendezvous.repository;

import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Optional<Company> findCompanyByUserEmail(String email);
}