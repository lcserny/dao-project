package net.cserny.tdd.dao;

import java.util.List;

public interface PersonDao {
    Person find(int id);

    void save(Person person);

    void update(Person person);

    void delete(Person person);

    List<Person> findAll();

    List<Person> findByLastname(String lastname);
}
