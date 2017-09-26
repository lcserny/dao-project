package net.cserny.tdd.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Person(resultSet.getString("first_name"),
                          resultSet.getString("last_name"));
    }
}
