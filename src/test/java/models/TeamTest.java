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
        Team testTeam = createTestTeam();
        assertTrue(testTeam.getName() instanceof String);
    }

    @Test
    public void getDescription_returnsStringWhenCalled_String() throws Exception {
        Team testTeam = createTestTeam();
        assertTrue(testTeam.getDescription() instanceof String);
    }

    public Team createTestTeam(){
        return new Team("TestTeam1", "TestDesc1");
    }


}