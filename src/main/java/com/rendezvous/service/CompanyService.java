/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.service;

import com.rendezvous.customexception.IncorrectWorkingHours;
import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.model.WorkDayHours;
import com.rendezvous.model.WorkWeek;
import com.rendezvous.repository.AvailabilityRepository;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.repository.RoleRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AvailabilityRepository availabilityRepository;

    public Company findCompanyByEmail(String email) {
        Optional<Company> company = companyRepository.findCompanyByUserEmail(email);
        company.orElseThrow(() -> new UsernameNotFoundException("Company " + email + " not found!"));
        return company.get();
    }

    public void saveCompany(Company company) {
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_COMPANY")) {
                company.getUser().setRoleList(Arrays.asList(a));
            }
        }
        String encodedPassword = bCryptPasswordEncoder.encode(company.getUser().getPassword());
        company.getUser().setPassword(encodedPassword);
        companyRepository.save(company);
    }

    //reads a company's avalailable hours, and creates a map. Keys are day1, day2, day3 etc.
    public WorkWeek findWorkingHoursByCompany(Company company) {
        List<Availability> availabilities = availabilityRepository.findAllByCompany(company);

        Map<String, WorkDayHours> week = new HashMap<>();

        for (int i = 1; i <= 7; i++) {
            week.put(String.valueOf(i), null);
        }

        for (Availability av : availabilities) {
            WorkDayHours wdh = new WorkDayHours(av.getOpenTime(), av.getCloseTime());
            week.put(String.valueOf(av.getWeekDay()), wdh);
        }

        WorkWeek workWeek = new WorkWeek(week);

        return workWeek;
    }

    public void saveWorkingHours(Company company, WorkWeek workWeek) throws IncorrectWorkingHours {
        for (Map.Entry<String, WorkDayHours> entry : workWeek.getWeek().entrySet()) {
            System.out.println("Key = " + entry.getKey()
                    + ", Value = " + entry.getValue());

            Integer weekDay = Integer.parseInt(entry.getKey());
            WorkDayHours hours = entry.getValue();

            
            if (hours.getStartTime() == null && hours.getCloseTime() == null) {
                availabilityRepository.deleteByCompanyAndWeekDay(company,weekDay);
            } else if (hours.getStartTime() == null ^ hours.getCloseTime() == null){    //XOR operation
                throw new IncorrectWorkingHours("Both Opening Time and Closing Time must be selected");
            } else if (hours.getCloseTime().isBefore(hours.getStartTime())) {
                throw new IncorrectWorkingHours("Closing time can not be before Opening Time");
            } else {
                //save to db
                Availability day;
                Optional<Availability> dayOptional = availabilityRepository.findByCompanyAndWeekDay(company, weekDay);
                if (dayOptional.isPresent()) {
                    day = dayOptional.get();
                    day.setOpenTime(hours.getStartTime());
                    day.setCloseTime(hours.getCloseTime());
                    availabilityRepository.save(day);
                } else {
                    day = new Availability();
                    day.setOpenTime(hours.getStartTime());
                    day.setCloseTime(hours.getCloseTime());
                    day.setCompany(company);
                    day.setWeekDay(weekDay);
                    availabilityRepository.save(day);
                }
            }
        }
    }

    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

}
