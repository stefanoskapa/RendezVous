package com.rendezvous.model;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityCalendarProperties {

    private List<BusinessHoursGroup> businessHours;

    private List<BlockDate> blockDates;

    public AvailabilityCalendarProperties() {
        this.businessHours = new ArrayList();
        this.blockDates = new ArrayList();
    }

    public AvailabilityCalendarProperties(List<BusinessHoursGroup> businessHours, List<BlockDate> blockDates) {
        this.businessHours = businessHours;
        this.blockDates = blockDates;
    }

    public List<BlockDate> getBlockDates() {
        return blockDates;
    }

    public void setBlockDates(List<BlockDate> blockDates) {
        this.blockDates = blockDates;
    }

    public List<BusinessHoursGroup> getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(List<BusinessHoursGroup> businessHours) {
        this.businessHours = businessHours;
    }

}
