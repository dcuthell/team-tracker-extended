package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName_returnsStringWhenCalled_String() throws Exception {
        Team testTeam = new Team();
        assertTrue(testTeam.getName() instanceof String);
    }


}