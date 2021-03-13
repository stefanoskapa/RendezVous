/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWeek {
    
    private Map<String, WorkDayHours> week;

    public WorkWeek(Map<String, WorkDayHours> week) {
        this.week = week;
    }

    public Map<String, WorkDayHours> getWeek() {
        return week;
    }

    public void setWeek(Map<String, WorkDayHours> week) {
        this.week = week;
    }

    
    
}
