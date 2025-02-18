package br.com.nba.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "PLAYER_SEASON")
@Table(name = "PLAYER_SEASON")
public class PlayerSeason {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "player_season_seasonfk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Season season;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", nullable = false, foreignKey = @ForeignKey(name = "player_season_playerfk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;
}
