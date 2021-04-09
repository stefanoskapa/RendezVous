package com.rendezvous.model;

import java.time.LocalDateTime;

public class CompanyDate {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private CompanyExtendedProps extendedProps;

    public CompanyDate() {
    }

    public CompanyDate(String title, LocalDateTime start, LocalDateTime end, CompanyExtendedProps extendedProps) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.extendedProps = extendedProps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public CompanyExtendedProps getExtendedProps() {
        return extendedProps;
    }

    public void setExtendedProps(CompanyExtendedProps extendedProps) {
        this.extendedProps = extendedProps;
    }

}
