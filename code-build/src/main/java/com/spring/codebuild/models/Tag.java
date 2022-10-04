package com.spring.codebuild.models;

public class Tag {
    private int Id;
    private String Name;

    public Tag() {
    }

    public Tag(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(int id) {
        Id = id;
    }
}
