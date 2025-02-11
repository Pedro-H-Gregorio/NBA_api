package br.com.nba.entities;

import jakarta.persistence.*;

@Entity(name = "SEASON_TEAM")
@Table(name = "SEASON_TEAM")
public class SeasonTeam {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "season_team_seasonfk"))
    private Season season;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "season_team_teamfk"))
    private Team team;

    public SeasonTeam() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeasonTeam that = (SeasonTeam) o;
        return id.equals(that.id) && season.equals(that.season) && team.equals(that.team);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + season.hashCode();
        result = 31 * result + team.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SeasonTeam{" +
                "id='" + id + '\'' +
                ", season=" + season +
                ", team=" + team +
                '}';
    }
}
