package com.rendezvous.repository;

import com.rendezvous.entity.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findCompanyByUserEmail(String email);

    Optional<Company> findCompanyByUserId(int id);

    Optional<Company> findById(int id);

    List<Company> findByDisplayNameContainingIgnoreCaseOrAddrCityContainingIgnoreCaseOrTelContaining(String term1, String term2, String term3);

    List<Company> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE company SET premium = '1' WHERE (id = ?1)",
            nativeQuery = true
    )
    void savePremiumStatus(Integer id);

    @Query(
            value = "SELECT DISTINCT addr_City from company",
            nativeQuery = true
    )
    Optional<List<String>> findAllCities();
}
