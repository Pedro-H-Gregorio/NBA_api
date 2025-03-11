package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
import br.com.nba.api.repositories.interfaces.SeasonTeamRepository;
import br.com.nba.api.repositories.interfaces.TeamRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeasonTeamRepositoryTest {

    @Autowired
    private SeasonTeamRepository seasonTeamRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

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
        seasonRepository.save(season);
        teamRepository.save(team);
        assertDoesNotThrow(()-> seasonTeamRepository.save(seasonTeam));
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Optional<SeasonTeam> seasonTeam = seasonTeamRepository.findById("2024"+1610612737);
        assertNotNull(seasonTeam.get());
        System.out.println(seasonTeam);
    }

    @Test
    @Order(3)
    void testGetAll() throws PersistenciaDawException {
        List<SeasonTeam> seasonTeams = seasonTeamRepository.findAll();
        assertNotNull(seasonTeams);
        assertFalse(seasonTeams.isEmpty());
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(4)
    void testDelete() throws PersistenciaDawException {
        seasonTeamRepository.deleteById("2024"+1610612737);
        assertNull(seasonTeamRepository.findById("2024"+1610612737));
        seasonRepository.deleteById("2024");
        teamRepository.deleteById(1610612737);
    }
}
