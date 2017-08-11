package models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private ArrayList<String> members;
    private Integer id;
    private static ArrayList<Team> allTeams = new ArrayList<>();

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        this.members = new ArrayList<>();
        allTeams.add(this);
        this.id = allTeams.size();
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public ArrayList<String> getMembers(){
        return members;
    }

    public void addMember(String memberName){
        String newMember = memberName;
        members.add(newMember);
    }

    public Integer getId(){
        return id;
    }

    public static ArrayList<Team> getAllTeams(){
        return allTeams;
    }

    public static Team findTeamById(Integer index){
        return null;
// return allTeams.get(index);
    }

    public static void clearAllTeams(){
        allTeams.clear();
    }
}
