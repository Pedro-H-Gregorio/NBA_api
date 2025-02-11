package br.com.nba.entities;

import jakarta.persistence.*;

@Entity(name = "SEASON")
@Table(name = "SEASON")
public class Season {

    @Id
    @Column(name = "id",length = 6, nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "year", length = 7, nullable = false, updatable = false, unique = true)
    private String year;

    public Season() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Season season = (Season) o;
        return id.equals(season.id) && year.equals(season.year);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
