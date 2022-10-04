package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.ArticleDAO;
import com.spring.codebuild.Validators.ArticleValidator;
import com.spring.codebuild.models.Article;
import com.spring.codebuild.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends ArticleValidator {

    @Autowired
    private ArticleDAO articleDAO;

    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> showArticles(String tag) {
        List<Article> matchingArticles;

        if (tag.length() != 0) {
            List<Article> checkingArticles = articleDAO.getArticles();

            TagsFinder tagsFinder = new TagsFinder();
            matchingArticles = tagsFinder.findArticleTags(checkingArticles, tag);

            return matchingArticles;
        }

        matchingArticles = articleDAO.getArticles();
        return matchingArticles;
    }

    public ResponseEntity showArticle(int id) {
        Article article = articleDAO.getArticle(id);

        String idError = checkId(id, article);
        if (!idError.equals("")) {
            return ResponseEntity.ok(idError);
        }

        return ResponseEntity.ok(article);
    }

    public List<Article> getPopularArticles(int id) {
        List<Article> popularArticles = articleDAO.getPopularArticles();
        int sizePopularArticles = popularArticles.size();

        if (id != 0) {
            for (int i = 0; i < sizePopularArticles; i++) {
                if (popularArticles.get(i).getId() == id) {
                    popularArticles.remove(i);
                    return popularArticles;
                }
            }
        }

        popularArticles.remove(sizePopularArticles - 1);

        return popularArticles;
    }

    public List<Tag> getTags() {
        return articleDAO.getTags();
    }

    public String addViews(int id) {
        Article article = articleDAO.getArticle(id);

        String idError = checkId(id, article);
        if (!idError.equals("")) {
            return idError;
        }

        articleDAO.addView(article.getViews(), id);

        return "Успешно";
    }
}
