/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rendezvous.model;

/**
 *
 * @author Leyteris
 */
public class ClientExtendedProps {

    private String addr_str;
    private String addr_no;
    private String addr_city;
    private String tel;

    public ClientExtendedProps() {
    }

    public ClientExtendedProps(String addr_str, String addr_no, String addr_city, String tel) {
        this.addr_str = addr_str;
        this.addr_no = addr_no;
        this.addr_city = addr_city;
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr_str() {
        return addr_str;
    }

    public void setAddr_str(String addr_str) {
        this.addr_str = addr_str;
    }

    public String getAddr_no() {
        return addr_no;
    }

    public void setAddr_no(String addr_no) {
        this.addr_no = addr_no;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }
    
    
}
