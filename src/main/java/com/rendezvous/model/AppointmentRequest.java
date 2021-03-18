/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.model;

import java.time.LocalDateTime;

/**
 *
 * @author Leyteris
 */
public class AppointmentRequest {
    private Integer companyId;
    private LocalDateTime appointmentTimestamp;

    public AppointmentRequest() {
    }

    public AppointmentRequest(Integer companyId, LocalDateTime appointmentTimestamp) {
        this.companyId = companyId;
        this.appointmentTimestamp = appointmentTimestamp;
    }

    public LocalDateTime getAppointmentTimestamp() {
        return appointmentTimestamp;
    }

    public void setAppointmentTimestamp(LocalDateTime appointmentTimestamp) {
        this.appointmentTimestamp = appointmentTimestamp;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppointmentRequest{companyId=").append(companyId);
        sb.append(", appointmentTimestamp=").append(appointmentTimestamp);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
