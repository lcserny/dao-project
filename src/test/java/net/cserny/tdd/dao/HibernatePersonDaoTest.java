package net.cserny.tdd.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

public class HibernatePersonDaoTest {
    private SessionFactory factory;
    private Session session;
    private Query query;

    @Before
    public void setUp() throws Exception {
        factory = createMock(SessionFactory.class);
        session = createMock(Session.class);
        query = createMock(Query.class);
    }

    @Test
    public void testFindByLastName() throws Exception {
        String hql = "from Person p where p.lastname = :lastname";
        String name = "Smith";

        List<Person> smiths = new ArrayList<Person>();
        smiths.add(new Person("Alice", name));
        smiths.add(new Person("Billy", name));
        smiths.add(new Person("Clark", name));

        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("lastname", name)).andReturn(query);
        expect(query.list()).andReturn(smiths);
        replay(factory, session, query);

        HibernatePersonDao personDao = new HibernatePersonDao();
        personDao.setSessionFactory(factory);
        List<Person> resultList = personDao.findByLastname(name);

        Assert.assertEquals(smiths, resultList);
        verify(factory, session, query);
    }
}
