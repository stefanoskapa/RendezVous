package com.rendezvous.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessHoursGroup {

    private List<Integer> daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public BusinessHoursGroup(List<Integer> daysOfWeek, LocalTime startTime, LocalTime endTime) {
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BusinessHoursGroup() {
        this.daysOfWeek = new ArrayList();
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}
