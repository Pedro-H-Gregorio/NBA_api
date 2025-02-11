package br.com.nba.entities;

import jakarta.persistence.*;

@Entity(name = "GAME")
@Table(name = "GAME")
public class Game {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;

    @Column(name = "matchup", nullable = false)
    private String matchup;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "game_seasonfk"))
    private Season season;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id", nullable = false, foreignKey = @ForeignKey(name = "game_team_homefk"))
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id", nullable = false, foreignKey = @ForeignKey(name = "game_team_awayfk"))
    private Team awayTeam;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_team_id", nullable = false, foreignKey = @ForeignKey(name = "game_team_winnerfk"))
    private Team winnerTeam;

    public Game() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchup() {
        return matchup;
    }

    public void setMatchup(String matchup) {
        this.matchup = matchup;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;
        return id.equals(game.id) && season.equals(game.season);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + season.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", matchup='" + matchup + '\'' +
                ", season=" + season +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", winnerTeam=" + winnerTeam +
                '}';
    }
}
