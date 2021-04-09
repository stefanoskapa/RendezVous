package com.rendezvous.model;

import java.time.LocalDateTime;

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
