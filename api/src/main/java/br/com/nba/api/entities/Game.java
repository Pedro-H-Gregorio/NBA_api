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
@Entity(name = "GAME")
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private String id;

    @Column(name = "matchup", nullable = false)
    private String matchup;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "game_seasonfk"))
    private Season season;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "home_team_id", nullable = false, foreignKey = @ForeignKey(name = "game_team_homefk"))
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "away_team_id", nullable = false, foreignKey = @ForeignKey(name = "game_team_awayfk"))
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "winner_team_id", foreignKey = @ForeignKey(name = "game_team_winnerfk"))
    private Team winnerTeam;
}
