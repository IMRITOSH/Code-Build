package com.spring.codebuild.models;

public class Course {
    private int Id;
    private String Name;
    private String Lessons;
    private String Tags;
    private int Views;

    public Course() {
    }

    public Course(int Id, String Name, String Lessons, String Tags, int Views) {
        this.Id = Id;
        this.Name = Name;
        this.Lessons = Lessons;
        this.Tags = Tags;
        this.Views = Views;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getLessons() {
        return Lessons;
    }

    public String getTags() {
        return Tags;
    }

    public int getViews() {
        return Views;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setLessons(String lessons) {
        Lessons = lessons;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public void setViews(int views) {
        Views = views;
    }
}
