package com.rendezvous.model;

public class SearchResult {

    private int id;
    private String displayName;

    private String addrStr;
    private String addrNo;
    private String addrCity;
    private String tel;

    public SearchResult(int id, String displayName, String addrStr, String addrNo, String addrCity, String tel) {
        this.id = id;
        this.displayName = displayName;
        this.addrStr = addrStr;
        this.addrNo = addrNo;
        this.addrCity = addrCity;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddrStr() {
        return addrStr;
    }

    public void setAddrStr(String addrStr) {
        this.addrStr = addrStr;
    }

    public String getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(String addrNo) {
        this.addrNo = addrNo;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
