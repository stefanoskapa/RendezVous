package com.rendezvous.service;

import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public void saveAppointment(Client client, Company company, LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        int timeslot = dateTime.toLocalTime().getHour();
        appointmentRepository.save(new Appointment(date, timeslot, client, company));
    }

    public List<Appointment> findByClient(Client client) {
        return appointmentRepository.findByClient(client);
    }

    public List<Appointment> findByCompany(Company company) {
        return appointmentRepository.findByCompany(company);
    }

    public boolean isRequestedAppointmentInThePast(LocalDateTime appointmentTimestamp) {
        return appointmentTimestamp.isBefore(LocalDateTime.now());
    }

    public void deleteByClientAndDateAndTimeslot(Client client, LocalDate date, int timeslot) {
        appointmentRepository.deleteByClientAndDateAndTimeslot(client, date, timeslot);
    }

    public boolean existsByClientAndDateAndTimeslot(Client client, LocalDate appDate, int timeslot) {
        return appointmentRepository.existsByClientAndDateAndTimeslot(client, appDate, timeslot);
    }

}
