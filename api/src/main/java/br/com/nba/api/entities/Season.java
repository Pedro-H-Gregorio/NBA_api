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
@Entity(name = "SEASON")
@Table(name = "SEASON")
public class Season {
    @Id
    @Column(name = "id", length = 6, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "year", length = 7, nullable = false, unique = true)
    private String year;
}
