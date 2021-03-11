/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.service;

import com.rendezvous.entity.Company;
import com.rendezvous.repository.CompanyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    
    public Company findCompanyByEmail(String email){
        Optional<Company> company = companyRepository.findCompanyByUserEmail(email);
        company.orElseThrow(()-> new UsernameNotFoundException("Company " + email + " not found!"));
        return company.get();
    }
}
