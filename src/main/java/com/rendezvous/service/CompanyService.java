/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.service;

import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Company;
import com.rendezvous.model.WorkDayHours;
import com.rendezvous.model.WorkWeek;
import com.rendezvous.repository.AvailabilityRepository;
import com.rendezvous.repository.CompanyRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AvailabilityRepository availabilityRepository;

    public Company findCompanyByEmail(String email) {
        Optional<Company> company = companyRepository.findCompanyByUserEmail(email);
        company.orElseThrow(() -> new UsernameNotFoundException("Company " + email + " not found!"));
        return company.get();
    }

    //reads a company's avalailable hours, and creates a map. Keys are day1, day2, day3 etc.
    public WorkWeek findWorkingHoursByCompany(Company company) {
        List<Availability> availabilities = availabilityRepository.findAllByCompany(company);

        Map<String, WorkDayHours> week = new HashMap<>();

        for (int i = 1; i <= 7; i++) {
            week.put("day" + i, null);
        }
     
        for (Availability av : availabilities) {
            WorkDayHours wdh = new WorkDayHours(av.getOpenTime(), av.getCloseTime());
            week.put("day"+av.getWeekDay(), wdh);
        }
        
        WorkWeek workWeek = new WorkWeek(week);

        return workWeek;
    }
}
