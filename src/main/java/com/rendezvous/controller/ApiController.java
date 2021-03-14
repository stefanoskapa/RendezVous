/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.entity.User;
import com.rendezvous.model.ClientCalendarProperties;
import com.rendezvous.model.CompanyCalendarProperties;
import com.rendezvous.model.WorkDayHours;
import com.rendezvous.repository.AppointmentRepository;
import com.rendezvous.repository.AvailabilityRepository;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.repository.UserRepository;
import com.rendezvous.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/") //todo add /api/v1/client and /api/v1/company in Spring Security
public class ApiController {

    
    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    ClientService clientService;
    //todo:
//    
    //calendar client, GET /client/dates
    //calentar company, GET /company/dates
//    
    //calendar selidas company_date_pick, emfanisi eleutheon rantevou, GET /client/company/{company_id}/availability
    //calendar selidas company_date_pick, epilogi rantevou, POST /client/company/{company_id}/date
//    
    //search company page, POST /client/comp-search/ (posting json obj with criteria https://stackoverflow.com/questions/5020704/how-to-design-restful-search-filtering?answertab=votes#tab-top)

    @GetMapping("/availability") //TODO We still need to figure out what type of object to return
    public List<WorkDayHours> fetchWorkingHours(@RequestParam int id) {
        Company company = companyRepository.findById(id).get();
        System.out.println("Company found: " + company.getDisplayName());
        List<Availability> workingHours = availabilityRepository.findAllByCompany(company);
        List<WorkDayHours> workDayHours = new ArrayList<>();
        for (Availability a : workingHours) {
            workDayHours.add(new WorkDayHours(a.getOpenTime(), a.getCloseTime()));
        }
        return workDayHours;
    }

  

    @GetMapping("/client/dates") 
    public List<ClientCalendarProperties> fetchClientAppointments() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) { //find out who is asking 
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }      
        Optional <Client> client = clientRepository.findClientByUserEmail(username);       
        return clientService.convertPropertiesList(appointmentRepository.findByClient(client.get()));      
    }
    
    
//    
//    @GetMapping("/client/dates")
//    public List<CompanyCalendarProperties> fetchCompanyAppointments() {
//        //todo
//        
//        return ResponseEntity.ok(lista me CompanyCalendarProperties);
//    }
}
