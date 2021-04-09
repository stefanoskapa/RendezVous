package com.rendezvous.model;

public class ClientExtendedProps {

    private String addr_str;
    private String addr_no;
    private String addr_city;
    private String tel;
    private Integer category;

    public ClientExtendedProps() {
    }

    public ClientExtendedProps(String addr_str, String addr_no, String addr_city, String tel, Integer category) {
        this.addr_str = addr_str;
        this.addr_no = addr_no;
        this.addr_city = addr_city;
        this.tel = tel;
        this.category = category;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
