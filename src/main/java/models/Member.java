package models;

public class Member {
    private String first;
    private String last;
    private Integer id;
    private Integer teamId;

    public Member(String first, String last, Integer teamId){
        this.first = first;
        this.last = last;
        this.teamId = teamId;
    }
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (!first.equals(member.first)) return false;
        if (!last.equals(member.last)) return false;
        if (id != null ? !id.equals(member.id) : member.id != null) return false;
        return teamId.equals(member.teamId);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + last.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + teamId.hashCode();
        return result;
    }
}
