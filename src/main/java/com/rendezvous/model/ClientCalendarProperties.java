package com.rendezvous.model;

import java.time.LocalDateTime;

public class ClientCalendarProperties {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private ClientExtendedProps extendedProps;

    public ClientCalendarProperties() {
    }

    public ClientCalendarProperties(String title, LocalDateTime start, LocalDateTime end, ClientExtendedProps extendedProps) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.extendedProps = extendedProps;
    }

    public ClientExtendedProps getExtendedProps() {
        return extendedProps;
    }

    public void setExtendedProps(ClientExtendedProps extendedProps) {
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

}
