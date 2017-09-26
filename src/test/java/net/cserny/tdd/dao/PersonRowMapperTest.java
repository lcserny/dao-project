package net.cserny.tdd.dao;

import com.mockobjects.sql.MockSingleRowResultSet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PersonRowMapperTest {
    @Test
    public void testMappingRow() throws Exception {
        Person expected = new Person("John", "Doe");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first_name", expected.getFirstName());
        data.put("last_name", expected.getLastName());
        MockSingleRowResultSet rs = new MockSingleRowResultSet();
        rs.addExpectedNamedValues(data);

        Assert.assertEquals(expected, new PersonRowMapper().mapRow(rs, 1));
    }
}
