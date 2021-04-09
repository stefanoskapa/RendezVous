package com.rendezvous.repository;

import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Company;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    List<Availability> findAllByCompany(Company company);

    void deleteByCompanyAndWeekDay(Company company, Integer weekDay);

    Optional<Availability> findByCompanyAndWeekDay(Company company, int parseInt);
}
