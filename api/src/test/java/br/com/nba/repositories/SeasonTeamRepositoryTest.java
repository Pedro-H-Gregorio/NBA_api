package br.com.nba.repositories;

import br.com.nba.entities.Season;
import br.com.nba.entities.SeasonTeam;
import br.com.nba.entities.Team;
import br.com.nba.repositories.impl.SeasonTeamRepositoryImpl;
import br.com.nba.repositories.interfaces.SeasonTeamRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeasonTeamRepositoryTest {
    private SeasonTeamRepository seasonTeamRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        seasonTeamRepository = new SeasonTeamRepositoryImpl(emf);
    }

    @AfterAll
    public void tearDown() throws Exception {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

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
