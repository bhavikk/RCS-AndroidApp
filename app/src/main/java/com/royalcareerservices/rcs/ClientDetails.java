package com.royalcareerservices.rcs;

import android.os.Parcel;
import android.os.Parcelable;
import com.royalcareerservices.rcs.Post;
import java.util.ArrayList;

public class ClientDetails{
    private long Id;
    private String Name;
    private String Openings;
    private ArrayList<Post> Post;
    private ArrayList<String> Description;
    private String url;
    private ArrayList<Quiz> Quiz;

    public ArrayList<Quiz> getQuiz() {
        return Quiz;
    }

    public void setQuiz(ArrayList<Quiz> quiz) {
        Quiz = quiz;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<Post> getPost() {
        return Post;
    }

    public void setPost(ArrayList<Post> post) {
        Post = post;
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
