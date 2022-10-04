package com.spring.codebuild.Validators;

import com.spring.codebuild.models.Article;

public class ArticleValidator {
    public String checkId(int id, Article dataBaseArticle){

        if(id == 0){
            return "Id should not be empty";
        } else if(dataBaseArticle==null){
            return "This id does not exist";
        }

        return "";
    }
}
