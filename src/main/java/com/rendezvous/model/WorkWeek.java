package com.rendezvous.model;

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
