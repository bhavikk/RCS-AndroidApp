package com.royalcareerservices.rcs;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    private String Name;
    private ArrayList<String> States;
    private String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<String> getStates() {
        return States;
    }

    public void setStates(ArrayList<String> states) {
        States = states;
    }
}
