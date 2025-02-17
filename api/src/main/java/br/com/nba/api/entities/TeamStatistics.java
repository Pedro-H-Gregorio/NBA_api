package br.com.nba.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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
}
