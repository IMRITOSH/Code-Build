package com.spring.codebuild.DAO;

import com.spring.codebuild.models.Course;
import com.spring.codebuild.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Course getCourse(int id) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE Id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Course.class))
                .stream().findAny().orElse(null);
    }

    public List<Course> getCourses() {
        return jdbcTemplate.query("SELECT * FROM courses", new BeanPropertyRowMapper<>(Course.class));
    }

    public List<Tag> getTags() {
        return jdbcTemplate.query("SELECT * FROM course_tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    public List<Course> getPopularCourses() {
        return jdbcTemplate.query("SELECT * FROM courses ORDER BY Views DESC LIMIT 4", new BeanPropertyRowMapper<>(Course.class));
    }

    public void addView(int views, int id) {
        jdbcTemplate.update("UPDATE courses SET Views = ? WHERE Id=?", views + 1, id);
    }
}
