/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.model.AvailabilityCalendarProperties;
import com.rendezvous.model.ClientCalendarProperties;
import com.rendezvous.model.CompanyCalendarProperties;
import com.rendezvous.repository.AppointmentRepository;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/") //todo add /api/v1/client and /api/v1/company in Spring Security
public class ApiController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    //todo:
//    
    //calendar client, GET /client/dates
    //calentar company, GET /company/dates
//    
    //calendar selidas company_date_pick, emfanisi eleutheon rantevou, GET /client/company/{company_id}/availability
    //calendar selidas company_date_pick, epilogi rantevou, POST /client/company/{company_id}/date
//    
    //search company page, POST /client/comp-search/ (posting json obj with criteria https://stackoverflow.com/questions/5020704/how-to-design-restful-search-filtering?answertab=votes#tab-top)

    // @GetMapping("/availability") //TODO We still need to figure out what type of object to return
    //public List<WorkDayHours> fetchWorkingHours(@RequestParam int id) {
    //}
    @GetMapping("/client/dates")
    public ResponseEntity<List<ClientCalendarProperties>> fetchClientAppointments() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) { //find out who is asking 
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Client client = clientService.findClientByEmail(username);
        return new ResponseEntity<>(clientService.convertPropertiesList(appointmentRepository.findByClient(client)), HttpStatus.OK);
    }

    @GetMapping("/company/dates")
    public ResponseEntity<List<CompanyCalendarProperties>> fetchCompanyAppointments() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) { //find out who is asking 
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Company company = companyService.findCompanyByEmail(username);
        return new ResponseEntity<>(companyService.convertPropertiesList(appointmentRepository.findByCompany(company)), HttpStatus.OK);
    }

    @GetMapping("/client/company/{company_id}/availability")
    public ResponseEntity<AvailabilityCalendarProperties> fetchCompanyAvailability(Principal principal, @PathVariable String company_id) {
        AvailabilityCalendarProperties availabilityCalendarProperties = new AvailabilityCalendarProperties();

        Client client = null;
        Company company = null;

        if (principal != null) {
            client = clientService.findClientByEmail(principal.getName());
        } else {
            return ResponseEntity.badRequest().build();
        }
        
        try {
            company = companyService.findCompanyById(Integer.parseInt(company_id));
        } catch (CompanyIdNotFound ex) {
            return ResponseEntity.badRequest().build();
        }
        
        availabilityCalendarProperties = companyService.getAvailabilityCalendarProperties(company, client);

        return ResponseEntity.ok(availabilityCalendarProperties);
    }
    
    //
    @PostMapping("/client/company/{company_id}/date")
    public ResponseEntity<String> confirmAppointment(Principal principal, @PathVariable String company_id) {


        //todo check if date is accepted
        

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
