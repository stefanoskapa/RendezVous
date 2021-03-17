/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leyteris
 */
public class CompanyCalendarProperties {
    private List<CompanyDate> events;
    private List<BusinessHoursGroup> businessHours;

    public CompanyCalendarProperties() {
        this.events = new ArrayList();
        this.businessHours = new ArrayList();
    }

    public CompanyCalendarProperties(List<CompanyDate> events, List<BusinessHoursGroup> businessHours) {
        this.events = events;
        this.businessHours = businessHours;
    }

    public List<BusinessHoursGroup> getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(List<BusinessHoursGroup> businessHours) {
        this.businessHours = businessHours;
    }

    public List<CompanyDate> getEvents() {
        return events;
    }

    public void setEvents(List<CompanyDate> events) {
        this.events = events;
    }
    
    
}
