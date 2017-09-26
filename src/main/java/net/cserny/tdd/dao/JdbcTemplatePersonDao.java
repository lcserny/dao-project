package net.cserny.tdd.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplatePersonDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findByLastName(String lastName) {
        return null;
    }
}
