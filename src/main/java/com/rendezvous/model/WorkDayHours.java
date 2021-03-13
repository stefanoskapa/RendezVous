/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.model;

import java.time.LocalTime;
import javax.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;


public class WorkDayHours {
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeTime;

    public WorkDayHours() {
    }

    public WorkDayHours(LocalTime startTime, LocalTime closeTime) {
        this.startTime = startTime;
        this.closeTime = closeTime;
    }
    

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WorkDayHours{startTime=").append(startTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
