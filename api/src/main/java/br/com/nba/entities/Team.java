package br.com.nba.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "TEAM")
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "year_founded", nullable = false)
    private Integer yearFounded;

    @Column(name = "state", nullable = false)
    private String state;

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;
        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", nickname='" + nickname + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", yearFounded=" + yearFounded +
                ", state='" + state + '\'' +
                '}';
    }
}
