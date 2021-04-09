package com.rendezvous.controller;

import com.rendezvous.customexception.ClientIdNotFound;
import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.customexception.ConversationNotFound;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Conversation;
import com.rendezvous.entity.Messages;
import com.rendezvous.entity.User;
import com.rendezvous.model.AppointmentRequest;
import com.rendezvous.model.AvailabilityCalendarProperties;
import com.rendezvous.model.ClientCalendarProperties;
import com.rendezvous.model.CompanyCalendarProperties;
import com.rendezvous.model.Message;
import com.rendezvous.model.SearchResult;
import com.rendezvous.model.UserProps;
import com.rendezvous.service.AppointmentService;
import com.rendezvous.service.ClientService;
import com.rendezvous.service.CompanyService;
import com.rendezvous.service.ConversationService;
import com.rendezvous.service.MessagesService;
import com.rendezvous.service.UserService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private MessagesService messagesService;

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
        return new ResponseEntity<>(clientService.convertPropertiesList(appointmentService.findByClient(client)), HttpStatus.OK);
    }

    @GetMapping("/company/dates")
    public ResponseEntity<CompanyCalendarProperties> fetchCompanyAppointments() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) { //find out who is asking 
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Company company = companyService.findCompanyByEmail(username);
        CompanyCalendarProperties companyCalendarProperties = companyService.getCompanyCalendarProperites(company);
        return new ResponseEntity<>(companyCalendarProperties, HttpStatus.OK);
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

    @PostMapping("/client/request-app")
    public ResponseEntity<String> confirmAppointment(Principal principal, @RequestBody AppointmentRequest appointmentRequest) {
        System.out.println(appointmentRequest);
        System.out.println(appointmentRequest.getAppointmentTimestamp());
        System.out.println("is requested appointment before now?>>>>>" + appointmentRequest.getAppointmentTimestamp().isBefore(LocalDateTime.now()));

        Client client = null;
        Company company = null;

        if (principal != null) {
            client = clientService.findClientByEmail(principal.getName());
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //check if client is free on the requested timeslot
        boolean isClientOccupied = clientService.isOccupied(client, appointmentRequest.getAppointmentTimestamp());

        if (isClientOccupied) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //check if company exists
        try {
            company = companyService.findCompanyById(appointmentRequest.getCompanyId());
        } catch (CompanyIdNotFound ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //check if requested timeslot is inside company's working hour
        boolean isDateInBusinessHours = companyService.isDateInBusinessHours(company, appointmentRequest.getAppointmentTimestamp());
        
        if (!isDateInBusinessHours) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //check if company have free the requested timeslot
        boolean isCompanyOccupied = false;
        isCompanyOccupied = companyService.isOccupied(company, appointmentRequest.getAppointmentTimestamp());

        if (isCompanyOccupied) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //check if requested date is before the current time
        boolean isRequestedAppointmentInThePast = false;
        isRequestedAppointmentInThePast = appointmentService.isRequestedAppointmentInThePast(appointmentRequest.getAppointmentTimestamp());

        if (isRequestedAppointmentInThePast) {
            return new ResponseEntity("past-date", HttpStatus.BAD_REQUEST);
        }

        //if no error found in request, then save a new appointment
        appointmentService.saveAppointment(client, company, appointmentRequest.getAppointmentTimestamp());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("client/comp-search")
    public ResponseEntity<Set<SearchResult>> findCompanies(@RequestParam String searchTerm, @RequestParam String category, @RequestParam String city) {
        Set<SearchResult> results = companyService.companySearch(searchTerm, category);
        if (!"All".equals(city)) {
            results = companyService.filterByCity(results, city);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/conv") //returns list of conversation-partners
    public ResponseEntity<List<UserProps>> fetchConvPartners(Principal principal) throws ClientIdNotFound, CompanyIdNotFound {
        User tempUser = userService.findByEmail(principal.getName());
        List<Conversation> conv = conversationService.findByUserId(tempUser.getId());
        List<UserProps> convPartners = new LinkedList<>();
        for (Conversation a : conv) {
            UserProps up;
            if (tempUser.getRoleList().get(0).getRole().equals("ROLE_COMPANY")) {
                Client tempClient = clientService.findClientByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(tempClient.getFname(), tempClient.getLname(), tempClient.getUser().getEmail());
            } else {
                Company comp = companyService.findCompanyByUserId(a.getPartnerId(tempUser.getId()).getId());
                up = new UserProps(comp.getFname(), comp.getLname(), comp.getUser().getEmail(), comp.getDisplayName());
            }
            convPartners.add(up);
        }
        return new ResponseEntity<>(convPartners, HttpStatus.OK);
    }

    @GetMapping("/myprops") // send user information to himself
    public ResponseEntity<UserProps> fetchMyData(Principal principal) throws ClientIdNotFound, CompanyIdNotFound {
        User tempUser = userService.findByEmail(principal.getName());
        UserProps up;
        if (tempUser.getRoleList().get(0).getRole().equals("ROLE_CLIENT")) {
            Client tempClient = clientService.findClientByUserEmail(principal.getName());
            System.out.println("client found:" + tempClient.toString());
            up = new UserProps(tempClient.getFname(), tempClient.getLname(), tempClient.getUser().getEmail());
        } else {
            Company comp = companyService.findCompanyByUserEmail(principal.getName());
            up = new UserProps(comp.getFname(), comp.getLname(), comp.getUser().getEmail(), comp.getDisplayName());
        }
        return new ResponseEntity<>(up, HttpStatus.OK);
    }

    @GetMapping("/load/{userEmail}") // loads messages with particular conversation-partner
    public ResponseEntity<List<Message>> fetchMessages(@PathVariable String userEmail, Principal principal) throws ConversationNotFound {
        User tempUser = userService.findByEmail(principal.getName());
        User otherUser = userService.findByEmail(userEmail);
        Optional<Conversation> tempConv = conversationService.findConversation(tempUser.getId(), otherUser.getId());
        List<Message> msgsToSend = new LinkedList<>();
        if (tempConv.isPresent()) {
            List<Messages> msgs = messagesService.findByConversationId(tempConv.get().getId()).get();
            for (Messages a : msgs) {
                String sender = tempUser.getId() == a.getUserId() ? principal.getName() : userEmail;
                Message om = new Message(sender, a.getMessage(), a.getTimestamp().toString());
                msgsToSend.add(om);
            }
        } else {
            conversationService.save(new Conversation(tempUser, otherUser));
        }
        return new ResponseEntity<>(msgsToSend, HttpStatus.OK);
    }

    @DeleteMapping("client/delete-app")
    public ResponseEntity deleteAppointment(Principal principal, @RequestBody LocalDateTime dateTimeToBeDeleted) {
        Client client = clientService.findClientByEmail(principal.getName());
        int timeslot = dateTimeToBeDeleted.getHour();
        LocalDate appDate = dateTimeToBeDeleted.toLocalDate();
        if (!appointmentService.existsByClientAndDateAndTimeslot(client, appDate, timeslot)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        appointmentService.deleteByClientAndDateAndTimeslot(client, appDate, timeslot);
        return new ResponseEntity(HttpStatus.OK);
    }

}
