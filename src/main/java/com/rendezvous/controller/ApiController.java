/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/") //todo add /api/v1/client and /api/v1/company in Spring Security
public class ApiController {

    //todo:
//    
    //calendar client, GET /client/dates
    //calentar company, GET /company/dates
//    
    //calendar selidas company_date_pick, emfanisi eleutheon rantevou, GET /client/company/{company_id}/availability
    //calendar selidas company_date_pick, epilogi rantevou, POST /client/company/{company_id}/date
//    
    //search company page, POST /client/comp-search/ (posting json obj with criteria https://stackoverflow.com/questions/5020704/how-to-design-restful-search-filtering?answertab=votes#tab-top)

}
