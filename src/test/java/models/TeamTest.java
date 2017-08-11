package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
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

    @Test
    public void getMembers_returnsArrayListWhenCalled_ArrayList() throws Exception {
        Team testTeam = createTestTeam();
        assertTrue(testTeam.getMembers() instanceof ArrayList);
    }

    @Test
    public void addMember_addsStringtoArrayMakingItSizeOf1_1() throws Exception {
        Team testTeam = createTestTeam();
        testTeam.addMember("Mike Jones");
        assertEquals(1, testTeam.getMembers().size());
    }

    @Test
    public void getAllTeams_addsTeamInstanceToStaticArray_1() throws Exception {
        Team testTeam = createTestTeam();
        assertEquals(1, Team.getAllTeams().size());
    }

    @Test
    public void getAllTeams_addsTwoTeamInstancesToStaticArray_2() throws Exception {
        Team testTeam = createTestTeam();
        Team testTeam2 = new Team("Test2", "TestDesc2");
        assertEquals(2, Team.getAllTeams().size());
    }

    @Test
    public void getId_firstInstanceHasIdOf1_1() throws Exception {
        Team testTeam = createTestTeam();
        Integer testId = 1;
        assertEquals(testId, testTeam.getId());
    }

    @Test
    public void getId_secondInstanceHasIdOf2_2() throws Exception {
        Team testTeam = createTestTeam();
        Team testTeam2 = new Team("Two", "two");
        Integer testId = 2;
        assertEquals(testId, testTeam2.getId());
    }

    @Test
    public void findTeamById_returnsCorrectTeamInstance() throws Exception {
        Team testTeam = createTestTeam();
        Team testTeam2 = new Team("Two", "two");
        assertEquals(testTeam2.getName(), Team.findTeamById(testTeam2.getId()).getName());
    }

    @Test
    public void setName_updatesTeamName() throws Exception {
        Team testTeam = createTestTeam();
        String newName = "TestName";
        testTeam.setName(newName);
        assertEquals(newName, testTeam.getName());
    }


    public Team createTestTeam(){
        return new Team("TestTeam1", "TestDesc1");
    }


}