
package com.rendezvous.dto;

import com.rendezvous.entity.Client;
import com.rendezvous.entity.User;
import java.util.Objects;


public class ClientUser {

 private String email;
 private String password;
 private String fname;
 private String lname;
 private String tel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public ClientUser(){}
    public ClientUser(String email, String password, String fname, String lname, String tel) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.fname);
        hash = 53 * hash + Objects.hashCode(this.lname);
        hash = 53 * hash + Objects.hashCode(this.tel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientUser other = (ClientUser) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.fname, other.fname)) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientUser{" + "email=" + email + ", password=" + password + ", fname=" + fname + ", lname=" + lname + ", tel=" + tel + '}';
    }
 
}
