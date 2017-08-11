package models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private ArrayList<String> members;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
        this.members = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public ArrayList<String> getMembers(){
        return null;
    }
}
