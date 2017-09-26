package net.cserny.tdd.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernatePersonDao implements PersonDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Person p where p.lastname = :lastname";
        Query query = session.createQuery(hql);
        query.setParameter("lastname", lastname);
        return query.list();
    }
}
