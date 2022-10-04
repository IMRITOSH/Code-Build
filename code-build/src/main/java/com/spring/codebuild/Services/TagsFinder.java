package com.spring.codebuild.Services;

import com.spring.codebuild.models.Article;
import com.spring.codebuild.models.Course;

import java.util.ArrayList;
import java.util.List;

public class TagsFinder {

    public List<Article> findArticleTags(List<Article> checkingArticles, String tag) {
        List<Article> matchingArticles = new ArrayList<>();

        for (int i = 0; i < checkingArticles.size(); i++) {
            String[] tagsArr = checkingArticles.get(i).getTags().split(",");

            for (String tagArr : tagsArr) {
                if (tagArr.equals(tag)) {
                    matchingArticles.add(checkingArticles.get(i));
                    break;
                }
            }
        }

        return matchingArticles;
    }

    public List<Course> findCourseTags(List<Course> checkingCourses, String tag) {
        List<Course> matchingCourses = new ArrayList<>();

        for (int i = 0; i < checkingCourses.size(); i++) {
            String[] tagsArr = checkingCourses.get(i).getTags().split(",");

            for (String tagArr : tagsArr) {
                if (tagArr.equals(tag)) {
                    matchingCourses.add(checkingCourses.get(i));
                    break;
                }
            }
        }

        return matchingCourses;
    }
}
