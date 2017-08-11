package models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private ArrayList<String> members;
    private static ArrayList<Team> allTeams;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        this.members = new ArrayList<>();
//        allTeams.add(this);
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

    public static ArrayList<Team> getAllTeams(){
        return allTeams;
    }
}
