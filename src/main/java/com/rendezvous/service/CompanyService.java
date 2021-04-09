package com.rendezvous.service;

import com.rendezvous.customexception.CompanyIdNotFound;
import com.rendezvous.customexception.IncorrectWorkingHours;
import com.rendezvous.entity.Appointment;
import com.rendezvous.entity.Availability;
import com.rendezvous.entity.Client;
import com.rendezvous.entity.Company;
import com.rendezvous.entity.Role;
import com.rendezvous.model.AvailabilityCalendarProperties;
import com.rendezvous.model.BlockDate;
import com.rendezvous.model.BusinessHoursGroup;
import com.rendezvous.model.CompanyCalendarProperties;
import com.rendezvous.model.CompanyDate;
import com.rendezvous.model.CompanyExtendedProps;
import com.rendezvous.model.SearchResult;
import com.rendezvous.model.WorkDayHours;
import com.rendezvous.model.WorkWeek;
import com.rendezvous.repository.AppointmentRepository;
import com.rendezvous.repository.AvailabilityRepository;
import com.rendezvous.repository.CompanyRepository;
import com.rendezvous.repository.RoleRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Company findCompanyByEmail(String email) {
        Optional<Company> company = companyRepository.findCompanyByUserEmail(email);
        company.orElseThrow(() -> new UsernameNotFoundException("Company " + email + " not found!"));
        return company.get();
    }

    public Company findCompanyById(Integer id) throws CompanyIdNotFound {
        Optional<Company> company = companyRepository.findById(id);
        company.orElseThrow(() -> new CompanyIdNotFound("Company " + id + " not found!"));
        return company.get();
    }

    public Company findCompanyByUserId(Integer userId) throws CompanyIdNotFound {
        Optional<Company> comp = companyRepository.findCompanyByUserId(userId);
        return comp.orElseThrow(() -> new CompanyIdNotFound("Company with userID=" + userId + " not found!"));
    }

    public Company findCompanyByUserEmail(String email) throws CompanyIdNotFound {
        Optional<Company> comp = companyRepository.findCompanyByUserEmail(email);
        return comp.orElseThrow(() -> new CompanyIdNotFound("Company with email=" + email + " not found!"));
    }

    public void saveCompany(Company company) {
        List<Role> roles = roleRepository.findAll();
        for (Role a : roles) {
            if (a.getRole().equals("ROLE_COMPANY")) {
                company.getUser().setRoleList(Arrays.asList(a));
            }
        }
        String encodedPassword = bCryptPasswordEncoder.encode(company.getUser().getPassword());
        company.getUser().setPassword(encodedPassword);
        company.setPremium(false);
        companyRepository.save(company);
    }

    //reads a company's avalailable hours, and creates a map. Keys are day1, day2, day3 etc.
    public WorkWeek findWorkingHoursByCompany(Company company) {
        List<Availability> availabilities = availabilityRepository.findAllByCompany(company);

        Map<String, WorkDayHours> week = new HashMap<>();

        for (int i = 0; i <= 6; i++) {
            week.put(String.valueOf(i), null);
        }

        for (Availability av : availabilities) {
            WorkDayHours wdh = new WorkDayHours(av.getOpenTime(), av.getCloseTime());
            week.put(String.valueOf(av.getWeekDay()), wdh);
        }

        WorkWeek workWeek = new WorkWeek(week);

        return workWeek;
    }

    public List<BusinessHoursGroup> getBusinessHours(WorkWeek workWeek) {

        List<BusinessHoursGroup> businessHours = new ArrayList();

        for (Map.Entry<String, WorkDayHours> entry : workWeek.getWeek().entrySet()) {

            Integer weekDay = Integer.parseInt(entry.getKey());
            WorkDayHours hours = entry.getValue();

            if (hours != null) {
                BusinessHoursGroup businessHoursGroup = new BusinessHoursGroup();

                if (weekDay == 7) {
                    businessHoursGroup.getDaysOfWeek().add(0);
                } else {
                    businessHoursGroup.getDaysOfWeek().add(weekDay);
                }
                businessHoursGroup.setStartTime(hours.getStartTime());
                businessHoursGroup.setEndTime(hours.getCloseTime());

                businessHours.add(businessHoursGroup);
            }
        }
        return businessHours;
    }

    public void saveWorkingHours(Company company, WorkWeek workWeek) throws IncorrectWorkingHours {
        for (Map.Entry<String, WorkDayHours> entry : workWeek.getWeek().entrySet()) {
            System.out.println("Key = " + entry.getKey()
                    + ", Value = " + entry.getValue());

            Integer weekDay = Integer.parseInt(entry.getKey());
            WorkDayHours hours = entry.getValue();

            if (hours.getStartTime() == null && hours.getCloseTime() == null) {
                availabilityRepository.deleteByCompanyAndWeekDay(company, weekDay);
            } else if (hours.getStartTime() == null ^ hours.getCloseTime() == null) {    //XOR operation
                throw new IncorrectWorkingHours("Both Opening Time and Closing Time must be selected");
            } else if (hours.getCloseTime().isBefore(hours.getStartTime())) {
                throw new IncorrectWorkingHours("Closing time can not be before Opening Time");
            } else {
                //save to db
                Availability day;
                Optional<Availability> dayOptional = availabilityRepository.findByCompanyAndWeekDay(company, weekDay);
                if (dayOptional.isPresent()) {
                    day = dayOptional.get();
                    day.setOpenTime(hours.getStartTime());
                    day.setCloseTime(hours.getCloseTime());
                    availabilityRepository.save(day);
                } else {
                    day = new Availability();
                    day.setOpenTime(hours.getStartTime());
                    day.setCloseTime(hours.getCloseTime());
                    day.setCompany(company);
                    day.setWeekDay(weekDay);
                    availabilityRepository.save(day);
                }
            }
        }
    }

    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    public CompanyCalendarProperties getCompanyCalendarProperites(Company company) {

        CompanyCalendarProperties companyCalendarProperties = new CompanyCalendarProperties();

        //adding company events
        List<Appointment> companyAppointments = appointmentRepository.findByCompany(company);
        LocalDateTime startTime;
        String fullname;
        for (Appointment ap : companyAppointments) {
            Client client = ap.getClient();
            startTime = ap.getDate().atStartOfDay();
            startTime = startTime.plusHours(ap.getTimeslot());
            CompanyExtendedProps cep = new CompanyExtendedProps(client.getTel());
            fullname = client.getFname() + " " + client.getLname();
            CompanyDate ccp = new CompanyDate(fullname, startTime, startTime.plusHours(1), cep);
            companyCalendarProperties.getEvents().add(ccp);
        }

        //finding and adding business hours
        WorkWeek workWeek = findWorkingHoursByCompany(company);
        companyCalendarProperties.setBusinessHours(getBusinessHours(workWeek));

        return companyCalendarProperties;
    }

    public AvailabilityCalendarProperties getAvailabilityCalendarProperties(Company company, Client client) {
        AvailabilityCalendarProperties availabilityCalendarProperties = new AvailabilityCalendarProperties();

        //finding and adding business hours
        WorkWeek workWeek = findWorkingHoursByCompany(company);
        availabilityCalendarProperties.setBusinessHours(getBusinessHours(workWeek));

        //blockDates will hold all events in the calendar(client events and company's blocked events with other clients)
        List<BlockDate> blockDates = new ArrayList();

        //finding and adding client events
        List<Appointment> clientAppointments = appointmentRepository.findByClient(client);

        for (Appointment ap : clientAppointments) {
            LocalDateTime startTime = ap.getDate().atStartOfDay();
            startTime = startTime.plusHours(ap.getTimeslot());

            LocalDateTime endTime = startTime.plusHours(1);

            String title = ap.getCompany().getDisplayName();

            if (title.equals(company.getDisplayName())) {
                blockDates.add(new BlockDate("Appointment with " + title + " booked", startTime, endTime));
            } else {
                blockDates.add(new BlockDate(title, startTime, endTime));
            }
        }

        //finding and adding company events
        List<Appointment> companyAppointments = appointmentRepository.findByCompany(company);

        for (Appointment ap : companyAppointments) {
            LocalDateTime startTime = ap.getDate().atStartOfDay();
            startTime = startTime.plusHours(ap.getTimeslot());

            LocalDateTime endTime = startTime.plusHours(1);

            //making sure client dates are showing above company dates
            if (!blockDates.contains(new BlockDate("", startTime, endTime))) {
                blockDates.add(new BlockDate("Unavailable", startTime, endTime));
            }
        }

        availabilityCalendarProperties.setBlockDates(blockDates);
        return availabilityCalendarProperties;
    }

    public Set<SearchResult> companySearch(String searchTerm, String category) {

        Set<SearchResult> results = new HashSet<>();
        List<Company> companies;
        if (searchTerm.trim().equals("")) {
            companies = companyRepository.findAll();
            for (Company comp : companies) {
                if (category.equals("All") || (comp.getCategory() != null && Integer.parseInt(category) == comp.getCategory().getId())) {
                    results.add(new SearchResult(comp.getId(), comp.getDisplayName(), comp.getAddrStr(), comp.getAddrNo(), comp.getAddrCity(), comp.getTel()));
                }
            }
        } else {
            String[] searchTerms = searchTerm.split(" ");
            for (String a : searchTerms) {
                companies = companyRepository.findByDisplayNameContainingIgnoreCaseOrAddrCityContainingIgnoreCaseOrTelContaining(a, a, a);
                for (Company comp : companies) {
                    if (category.equals("All") || (comp.getCategory() != null && Integer.parseInt(category) == comp.getCategory().getId())) {
                        results.add(new SearchResult(comp.getId(), comp.getDisplayName(), comp.getAddrStr(), comp.getAddrNo(), comp.getAddrCity(), comp.getTel()));
                    }
                }
            }
        }
        return results;
    }

    public boolean isOccupied(Company company, LocalDateTime appointmentTimestamp) {
        LocalDate reqDate = appointmentTimestamp.toLocalDate();
        Integer timeslot = appointmentTimestamp.getHour();

        return appointmentRepository.existsByCompanyAndDateAndTimeslot(company, reqDate, timeslot);
    }

    public boolean isDateInBusinessHours(Company company, LocalDateTime appointmentTimestamp) {

        //convert getDayOfWeek() output to db availability table 0 based working hours
        //getDayOfWeek().getValue() output 1-7(starting Mondey), db saves 0-6(starting Sunday)
//        System.out.println(appointmentTimestamp.getDayOfWeek().getValue());
//        int dayNumber = appointmentTimestamp.getDayOfWeek().getValue() == 7 ? 0 : appointmentTimestamp.getDayOfWeek().getValue();

        Optional<Availability> dayOpt = availabilityRepository.findByCompanyAndWeekDay(company, appointmentTimestamp.getDayOfWeek().getValue());

        if (!dayOpt.isPresent()) {
            //no Availability entry found for the spesific week day, so the company is closed for the entire day
            return false;
        } else {
            Availability day = dayOpt.get();
            LocalTime openTime = day.getOpenTime();
            LocalTime closeTime = day.getCloseTime();
            LocalTime requestedTime = appointmentTimestamp.toLocalTime();

            if (!openTime.isAfter(requestedTime) && closeTime.isAfter(requestedTime)) {
                //open in the requested week day, AND inside working hours
                return true;
            } else {
                //open in the requested week day, but outside working hours
                return false;
            }
        }

    }

    public void setPremiumStatus(Company company) {
        companyRepository.savePremiumStatus(company.getId());
    }

    public List<String> findAllCities() {
        return companyRepository.findAllCities().orElseThrow(() -> new UsernameNotFoundException("Cities not found!"));
    }

    public Set<SearchResult> filterByCity(Set<SearchResult> companies, String city) {
        Set<SearchResult> comps = new HashSet<>();
        for (SearchResult c : companies) {
            if (c.getAddrCity().trim().equals(city.trim())) {
                comps.add(c);
            }
        }
        return comps;
    }

}
