package com.spring.codebuild.models;

public class Article {
    private int id;
    private String title;
    private String text;
    private String authour;
    private String tags;
    private int views;

    public Article() {
    }

    public Article(int id, String title, String text, String authour, String tags, int views) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authour = authour;
        this.tags = tags;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAuthour() {
        return authour;
    }

    public String getTags() {
        return tags;
    }

    public int getViews() {
        return views;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthour(String authour) {
        this.authour = authour;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
