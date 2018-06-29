package com.royalcareerservices.rcs;

public class ClientDetails {
    private long Id;
    private String Name;
    private String Openings;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getOpenings() {
        return Openings;
    }

    public void setOpenings(String numberOpenings) {
        this.Openings = numberOpenings;
    }
}
