package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o); //ignore me for now

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingMemberSetsId() throws Exception {
        Member member = new Member("John", "Smith", 0);
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId()); //how does this work?
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = new Member ("mow the lawn");
        memberDao.add(member); //add to dao (takes care of saving)
        Member foundMember = memberDao.findById(member.getId()); //retrieve
        assertEquals(member, foundMember); //should be the same
    }
}