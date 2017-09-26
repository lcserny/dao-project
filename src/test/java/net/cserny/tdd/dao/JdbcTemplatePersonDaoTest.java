package net.cserny.tdd.dao;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

public class JdbcTemplatePersonDaoTest {
    @Test
    public void testFindByLastname() throws Exception {
        String lastName = "Smith";
        List<Person> smiths = createListOfPeopleNamed(lastName);
        JdbcTemplate template = createMock(JdbcTemplate.class);
        template.query(eq("SELECT * FROM employee WHERE last_name = ?"),
                       aryEq(new Object[]{lastName}),
                       isA(PersonRowMapper.class));
        expectLastCall().andReturn(smiths);

        replay(template);

        JdbcTemplatePersonDao dao = new JdbcTemplatePersonDao();
        dao.setJdbcTemplate(template);

        Assert.assertEquals(smiths, dao.findByLastName(lastName));

        verify(template);
    }

    private List<Person> createListOfPeopleNamed(String lastName) {
        List<Person> expected = new ArrayList<Person>();
        expected.add(new Person("Alice", lastName));
        expected.add(new Person("Billy", lastName));
        expected.add(new Person("Leo", lastName));
        return expected;
    }
}
