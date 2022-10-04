package com.spring.codebuild.DAO;

import com.spring.codebuild.models.Article;
import com.spring.codebuild.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getArticles() {
        return jdbcTemplate.query("SELECT * FROM articles", new BeanPropertyRowMapper<>(Article.class));
    }

    public Article getArticle(int id) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE Id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Article.class))
                .stream().findAny().orElse(null);
    }

    public List<Tag> getTags() {
        return jdbcTemplate.query("SELECT * FROM article_tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    public List<Article> getPopularArticles() {
        return jdbcTemplate.query("SELECT * FROM articles ORDER BY Views DESC LIMIT 4", new BeanPropertyRowMapper<>(Article.class));
    }

    public void addView(int views, int id) {
        jdbcTemplate.update("UPDATE articles SET Views = ? WHERE Id=?", views + 1, id);
    }
}
