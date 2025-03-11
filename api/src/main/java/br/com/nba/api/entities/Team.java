package br.com.nba.api.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "TEAM")
@Table(name = "TEAM")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    List<TeamStatistics> statistics;
}
