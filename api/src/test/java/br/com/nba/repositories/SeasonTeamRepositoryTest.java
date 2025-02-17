package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.impl.SeasonTeamRepositoryImpl;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeasonTeamRepositoryTest {

    @Autowired
    private SeasonTeamRepository seasonTeamRepository;

    @Test
    @Order(1)
    void testSave() throws PersistenciaDawException {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        SeasonTeam seasonTeam = new SeasonTeam();
        seasonTeam.setId(season.getId() + team.getId().toString());
        seasonTeam.setTeam(team);
        seasonTeam.setSeason(season);
        assertDoesNotThrow(()-> seasonTeamRepository.save(seasonTeam)); ;
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        SeasonTeam seasonTeam = seasonTeamRepository.getByID("2024"+1610612737);
        assertNotNull(seasonTeam);
        System.out.println(seasonTeam);
    }

    @Test
    @Order(3)
    void testGetAll() throws PersistenciaDawException {
        List<SeasonTeam> seasonTeams = seasonTeamRepository.getAll();
        assertNotNull(seasonTeams);
        assertFalse(seasonTeams.isEmpty());
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(4)
    void testDelete() throws PersistenciaDawException {
        seasonTeamRepository.delete("2024"+1610612737);
        assertNull(seasonTeamRepository.getByID("2024"+1610612737));
    }
}
