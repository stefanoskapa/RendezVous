package com.rendezvous.repository;

import com.rendezvous.entity.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findCompanyByUserEmail(String email);

    Optional<Company> findById(int id);

    //@Query(
    //        value = "SELECT * FROM company c WHERE c.display_name LIKE '%?%';",
    //        nativeQuery = true)
    //List<Company> findCompaniesBySearch(String searchTerm);
    List<Company> findByDisplayNameContainingIgnoreCase(String searchTerm);
}
