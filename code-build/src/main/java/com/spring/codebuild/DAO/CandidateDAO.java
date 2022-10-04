package com.spring.codebuild.DAO;

import com.spring.codebuild.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CandidateDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CandidateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertInCandidates(User user) {
        jdbcTemplate.update("INSERT INTO candidates (Email, Password, Name, Version, life_time) VALUES(?, ?, ?, ?, current_timestamp+1 minute ", user.getEmail(),
                user.getPassword(), user.getName(), user.getVersion());
    }

    public User checkCandidate(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE Email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void checkConfirm() {
        jdbcTemplate.update("DELETE FROM candidates WHERE life_time < current_timestamp");
    }
}
