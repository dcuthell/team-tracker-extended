package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_addingMemberSetsId() throws Exception {
        Member member = createTestMember();
        Integer originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void findById_existingMembersCanBeFound() throws Exception {
        Member member = createTestMember();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void getAll_addedMembersAreReturned() throws Exception {
        Member member = createTestMember();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void getAll_noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void update_updateWorks() throws Exception {
        Member member = createTestMember();
        memberDao.add(member);
        memberDao.update(1, "Mike", "Jones");
        assertNotEquals("John", memberDao.findById(member.getId()).getFirst());
    }

    @Test
    public void deleteByMemberId_removesSelectedMember() throws Exception {
        Member member1 = createTestMember();
        memberDao.add(member1);
        Member member2 = new Member("Mike", "Jones", 1);
        memberDao.add(member2);
        memberDao.deleteMemberById(member2.getId());
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void clearAllMember_removesAllMembers() throws Exception {
        Member member1 = createTestMember();
        memberDao.add(member1);
        Member member2 = new Member("Mike", "Jones", 1);
        memberDao.add(member2);
        memberDao.clearAllMembers();
        assertEquals(0, memberDao.getAll().size());
    }


    public Member createTestMember(){
        return new Member("John", "Smith", 0);
    }

}
