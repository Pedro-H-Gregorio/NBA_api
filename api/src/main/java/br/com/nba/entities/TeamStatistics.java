package br.com.nba.entities;

import jakarta.persistence.*;

@Entity(name = "TEAM_STATISTICS")
@Table(name = "TEAM_STATISTICS")
public class TeamStatistics {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "wl", length = 50)
    private String wl;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "rebounds", nullable = false)
    private Integer rebounds;

    @Column(name = "blocks", nullable = false)
    private Integer blocks;

    @Column(name = "assistances", nullable = false)
    private Integer assistances;

    @Column(name = "steals", nullable = false)
    private Integer steals;

    @Column(name = "three_point_shots", nullable = false)
    private Integer threePointShots;

    @Column(name = "perimeter_baskets", nullable = false)
    private Integer perimeterBaskets;

    @Column(name = "free_throws", nullable = false)
    private Integer freeThrows;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "team_statistics_teamfk"))
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "team_statistics_gamefk"))
    private Game game;

    public TeamStatistics() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl = wl;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRebounds() {
        return rebounds;
    }

    public void setRebounds(Integer rebounds) {
        this.rebounds = rebounds;
    }

    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Integer getAssistances() {
        return assistances;
    }

    public void setAssistances(Integer assistances) {
        this.assistances = assistances;
    }

    public Integer getSteals() {
        return steals;
    }

    public void setSteals(Integer steals) {
        this.steals = steals;
    }

    public Integer getThreePointShots() {
        return threePointShots;
    }

    public void setThreePointShots(Integer threePointShots) {
        this.threePointShots = threePointShots;
    }

    public Integer getPerimeterBaskets() {
        return perimeterBaskets;
    }

    public void setPerimeterBaskets(Integer perimeterBaskets) {
        this.perimeterBaskets = perimeterBaskets;
    }

    public Integer getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(Integer freeThrows) {
        this.freeThrows = freeThrows;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatistics that = (TeamStatistics) o;
        return id.equals(that.id) && team.equals(that.team) && game.equals(that.game);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + team.hashCode();
        result = 31 * result + game.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TeamStatistics{" +
                "id='" + id + '\'' +
                ", wl='" + wl + '\'' +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", blocks=" + blocks +
                ", assistances=" + assistances +
                ", steals=" + steals +
                ", threePointShots=" + threePointShots +
                ", perimeterBaskets=" + perimeterBaskets +
                ", freeThrows=" + freeThrows +
                ", team=" + team +
                ", game=" + game +
                '}';
    }
}
