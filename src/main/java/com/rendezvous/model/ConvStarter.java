
package com.rendezvous.model;

import java.util.List;

// Supplies information to initialize the communication
public class ConvStarter {

    private String userId;
    private String idByRole; //clientId or CompanyId
    private String role;
    private String fname;
    private String lname;
    private String companyName;
    private List<ConvStarter> convPartners;

    public ConvStarter(String userId, String idByRole, String role, String fname, String lname) {
        this.userId = userId;
        this.idByRole = idByRole;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
    }
    
    public ConvStarter() {}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdByRole() {
        return idByRole;
    }

    public void setIdByRole(String idByRole) {
        this.idByRole = idByRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<ConvStarter> getConvPartners() {
        return convPartners;
    }

    public void setConvPartners(List<ConvStarter> convPartners) {
        this.convPartners = convPartners;
    }
    

}