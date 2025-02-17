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
}
