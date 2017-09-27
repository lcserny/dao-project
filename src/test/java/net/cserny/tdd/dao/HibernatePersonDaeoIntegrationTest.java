package net.cserny.tdd.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@PropertySource("classpath:application.properties")
public class HibernatePersonDaeoIntegrationTest {
    @Test
    public void persistedObjectExistsInDatabase() throws Exception {
        SessionFactory factory = getSessionFactory();
        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);

        Person person = new Person("John", "Doe");
        dao.save(person);
        Assert.assertNotNull(person.getId());

        Session session = factory.openSession();
        Object copy = session.get(Person.class, person.getId());
        Assert.assertEquals(person, copy);
    }

    private SessionFactory getSessionFactory() {
        Configuration configuration = createConfiguration();
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(registry);
    }

    private Configuration createConfiguration() {
        Configuration configuration = loadProductionConfiguration();
        configuration.setProperties(new Properties());
        return configuration;
    }

    private Configuration loadProductionConfiguration() {
        return new Configuration().configure();
    }

    private void loadTestConfiguration(Configuration configuration, String path) throws IOException {
        Properties properties = loadPropertiesFrom(path);
        for (Object key : properties.keySet()) {
            String keyString = (String) key;
            String value = properties.getProperty(keyString);
            configuration.setProperty(keyString, value);
        }
    }

    private Properties loadPropertiesFrom(String path) throws IOException {
        Properties properties = new Properties();
        try (InputStream stream = getClass().getResourceAsStream(path)) {
            Assert.assertNotNull("Resource not found: " + path, stream);
            properties.load(stream);
        }
        return properties;
    }
}
