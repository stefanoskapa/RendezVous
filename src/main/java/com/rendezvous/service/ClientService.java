/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.service;

import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.model.ClientCalendarProperties;
import com.rendezvous.model.ClientExtendedProps;
import com.rendezvous.repository.ClientRepository;
import com.rendezvous.repository.RoleRepository;
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
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client findClientByEmail(String email) {
        Optional<Client> client = clientRepository.findClientByUserEmail(email);
        client.orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
        return client.get();
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
            ClientExtendedProps cep = new ClientExtendedProps(comp.getAddrStr(), comp.getAddrNo(), comp.getAddrCity(), comp.getTel());
            ClientCalendarProperties ccp = new ClientCalendarProperties(comp.getDisplayName(), startTime, startTime.plusHours(1), cep);
            ccpList.add(ccp);
        }
        return ccpList;
    }
}
