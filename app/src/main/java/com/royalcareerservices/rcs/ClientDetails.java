package com.royalcareerservices.rcs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ClientDetails{
    private long Id;
    private String Name;
    private String Openings;
    private ArrayList<String> Post;
    private ArrayList<String> Description;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getDescription() {
        return Description;
    }

    public void setDescription(ArrayList<String> Description) {
        this.Description = Description;
    }

    public ArrayList<String> getPost() {
        return Post;
    }

    public void setPost(ArrayList<String> post) {
        Post = post;
    }

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
