package com.spring.codebuild.models;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;

    private int version;

    public User(int id, String name, String email, String password, int version) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.version = version;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
