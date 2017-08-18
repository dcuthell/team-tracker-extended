package dao;

import models.Member;

import java.util.List;

public interface MemberDao {

    //create
    void add (Member member);

    //read
    List<Member> getAll();
    Member findById(Integer id);

    //update
    void update(int id, String first, String last, int teamId);

    //delete
    void deleteMemberById(int id);
    void clearAllMembers();

}
