package net.cserny.tdd.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {
    private DataSource dataSource;

    public void setDatasource(DataSource datasource) {
        this.dataSource = datasource;
    }

    public Person find(int id) {
        return null;
    }

    public void save(Person person) {

    }

    public void update(Person person) {

    }

    public void delete(Person person) {

    }

    public List<Person> findAll() {
        return null;
    }

    public List<Person> findByLastname(String lastname) {
        try {
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM people WHERE last_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lastname);
            ResultSet resultSet = statement.executeQuery();
            List<Person> people = new ArrayList<Person>();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                people.add(new Person(firstName, lastName));
            }
            resultSet.close();
            statement.close();
            connection.close();

            return people;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
