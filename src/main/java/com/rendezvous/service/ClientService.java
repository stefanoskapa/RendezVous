package com.rendezvous.service;

import com.rendezvous.customexception.ClientIdNotFound;
import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.model.ClientCalendarProperties;
import com.rendezvous.model.ClientExtendedProps;
import com.rendezvous.repository.AppointmentRepository;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.RoleRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client findClientByEmail(String email) {
        Optional<Client> client = clientRepository.findClientByUserEmail(email);
        client.orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
        return client.get();
    }

    public Client findClientById(Integer id) throws ClientIdNotFound {
        Optional<Client> client = clientRepository.findById(id);
        client.orElseThrow(() -> new ClientIdNotFound("Client " + id + " not found!"));
        return client.get();
    }

    public Client findClientByUserId(Integer userId) throws ClientIdNotFound {
        Optional<Client> client = clientRepository.findClientByUserId(userId);
        return client.orElseThrow(() -> new ClientIdNotFound("Client with userID=" + userId + " not found!"));
    }

    public Client findClientByUserEmail(String email) throws ClientIdNotFound {
        Optional<Client> client = clientRepository.findClientByUserEmail(email);
        return client.orElseThrow(() -> new ClientIdNotFound("Client with email=" + email + " not found!"));
    }

    public void saveClient(Client client) {
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_CLIENT")) {
                client.getUser().setRoleList(Arrays.asList(a));
            }
        }
        String encodedPassword = bCryptPasswordEncoder.encode(client.getUser().getPassword());
        client.getUser().setPassword(encodedPassword);
        clientRepository.save(client);
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public List<ClientCalendarProperties> convertPropertiesList(List<Appointment> appointments) {
        List<ClientCalendarProperties> ccpList = new LinkedList<>();
        LocalDateTime startTime;
        for (Appointment ap : appointments) {
            Company comp = ap.getCompany();
            startTime = ap.getDate().atStartOfDay();
            startTime = startTime.plusHours(ap.getTimeslot());
            ClientExtendedProps cep = new ClientExtendedProps(
                    comp.getAddrStr(),
                    comp.getAddrNo(),
                    comp.getAddrCity(),
                    comp.getTel(),
                    comp.getCategory() != null ? comp.getCategory().getId() : null
            );
            ClientCalendarProperties ccp = new ClientCalendarProperties(comp.getDisplayName(), startTime, startTime.plusHours(1), cep);
            ccpList.add(ccp);
        }
        return ccpList;
    }

    public boolean isOccupied(Client client, LocalDateTime appointmentTimestamp) {
        LocalDate reqDate = appointmentTimestamp.toLocalDate();
        Integer timeslot = appointmentTimestamp.getHour();

        return appointmentRepository.existsByClientAndDateAndTimeslot(client, reqDate, timeslot);
    }
}
