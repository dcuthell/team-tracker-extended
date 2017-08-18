package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String description;
    private Integer id;
    private List<Member> members;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (!name.equals(team.name)) return false;
        if (!description.equals(team.description)) return false;
        return id != null ? id.equals(team.id) : team.id == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
