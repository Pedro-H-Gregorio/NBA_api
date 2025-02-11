package br.com.nba.entities;

import jakarta.persistence.*;

@Entity(name = "PLAYER_SEASON")
@Table(name = "PLAYER_SEASON")
public class PlayerSeason {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "player_season_seasonfk"))
    private Season season;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", nullable = false, foreignKey = @ForeignKey(name = "player_season_playerfk"))
    private Player player;

    public PlayerSeason() {
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSeason that = (PlayerSeason) o;
        return id.equals(that.id) && season.equals(that.season) && player.equals(that.player);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + season.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "playerSeason{" +
                "id='" + id + '\'' +
                ", season=" + season +
                ", player=" + player +
                '}';
    }
}
