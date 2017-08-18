package dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {

    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2o sql2o2 = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o2);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void add_addingTeamSetsId() throws Exception {
        Team team = createTestTeam();
        Integer originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void findById_existingTeamsCanBeFound() throws Exception {
        Team team = createTestTeam();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void getAll_addedTeamsAreReturned() throws Exception {
        Team team = createTestTeam();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void getAll_noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeam_returnsAllTeamMembersInTeam() throws Exception {
        Member member1 = new Member("John", "Smith", 1);
        memberDao.add(member1);
        Member member2 = new Member("Mike", "Jones", 1);
        memberDao.add(member2);
        assertEquals(2, teamDao.getAllMembersByTeam(member1.getTeamId()).size());
    }

    @Test
    public void update_updateWorks() throws Exception {
        Team team = createTestTeam();
        teamDao.add(team);
        teamDao.update(team.getId(),"NewName", "NewDesc");
        assertNotEquals("TestName", teamDao.findById(team.getId()).getName());
    }

    @Test
    public void deleteByTeamId_removesSelectedTeam() throws Exception {
        Team team1 = createTestTeam();
        teamDao.add(team1);
        Team team2 = new Team("TestName2", "TestDesc2");
        teamDao.add(team2);
        teamDao.deleteTeamById(team2.getId());
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void deleteAllMembersByTeam_clearsAllMembersFromTeam() throws Exception {
        Member member1 = new Member("John", "Smith", 1);
        memberDao.add(member1);
        Member member2 = new Member("Mike", "Jones", 1);
        memberDao.add(member2);
        Member member3 = new Member("Nazem", "Kadri", 2);
        memberDao.add(member3);
        teamDao.deleteAllMembersByTeam(1);
        assertEquals(0, teamDao.getAllMembersByTeam(1).size());
    }

    @Test
    public void clearAllTeam_removesAllTeams() throws Exception {
        Team team1 = createTestTeam();
        teamDao.add(team1);
        Team team2 = new Team("TestName2", "TestDesc2");
        teamDao.add(team2);
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    public Team createTestTeam(){
        return new Team("TestName", "TestDesc");
    }
}