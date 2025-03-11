package br.com.nba.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "PLAYER_STATISTICS")
@Table(name = "PLAYER_STATISTICS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PlayerStatistics {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "points")
    private Integer points;

    @Column(name = "rebounds")
    private Integer rebounds;

    @Column(name = "blocks")
    private Integer blocks;

    @Column(name = "assistances")
    private Integer assistances;

    @Column(name = "steals")
    private Integer steals;

    @Column(name = "three_point_shots")
    private Integer threePointShots;

    @Column(name = "perimeter_baskets")
    private Integer perimeterBaskets;

    @Column(name = "free_throws")
    private Integer freeThrows;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "player_statistics_playerfk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "player_statistics_gamefk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;
}
