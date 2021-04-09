package com.rendezvous.repository;

import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findByCompany(Company company);

    public List<Appointment> findByClient(Client client);

    boolean existsByClientAndDateAndTimeslot(Client client, LocalDate localDate, int timeslot);

    boolean existsByCompanyAndDateAndTimeslot(Company company, LocalDate localDate, int timeslot);

    void deleteByClientAndDateAndTimeslot(Client client, LocalDate localDate, int timeslot);
}
