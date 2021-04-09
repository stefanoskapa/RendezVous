package com.rendezvous.model;

public class UserProps {

    private String fname;
    private String lname;
    private String email;
    private String companyName;

    public UserProps() {
    }

    public UserProps(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public UserProps(String fname, String lname, String email, String companyName) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.companyName = companyName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
