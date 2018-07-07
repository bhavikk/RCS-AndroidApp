package com.royalcareerservices.rcs;

public class UserDetails {
    private String Name;
    private String Surname;
    private String Email;
    private long Contactno;

    public String getName() {
        return Name;

    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getContactno() {
        return Contactno;
    }

    public void setContactno(long contactno) {
        Contactno = contactno;
    }
}
