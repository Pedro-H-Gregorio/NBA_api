package br.com.nba.repositories;

import br.com.nba.entities.Game;
import br.com.nba.entities.Season;
import br.com.nba.entities.Team;
import br.com.nba.repositories.impl.GameRepositoryImpl;
import br.com.nba.repositories.interfaces.GameRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameRepositoryTest {
    private GameRepository gameRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        gameRepository = new GameRepositoryImpl(emf);
    }

    @AfterAll
    void tearDown() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    @Order(1)
    void testSave() throws PersistenciaDawException {
        Season season = new Season();
        season.setId("22022");
        season.setYear("2022-23");

        Team homeTeam = new Team();
        homeTeam.setId(1610612738);
        homeTeam.setCity("Boston");
        homeTeam.setNickname("Celtics");
        homeTeam.setAbbreviation("BOS");
        homeTeam.setFullName("Boston Celtics");
        homeTeam.setYearFounded(1946);
        homeTeam.setState("Massachusetts");

        Team awayTeam = new Team();
        awayTeam.setId(1610612755);
        awayTeam.setCity("Philadelphia");
        awayTeam.setNickname("76ers");
        awayTeam.setAbbreviation("PHI");
        awayTeam.setFullName("Philadelphia 76ers");
        awayTeam.setYearFounded(1949);
        awayTeam.setState("Pennsylvania");

        Game game = new Game();
        game.setId("0022200001");
        game.setMatchup("BOS vs. PHI");
        game.setSeason(season);
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setWinnerTeam(awayTeam);

        assertDoesNotThrow(() -> gameRepository.save(game));
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Game game = gameRepository.getByID("0022200001");
        assertNotNull(game);
        assertEquals("0022200001", game.getId());
        assertEquals("BOS vs. PHI", game.getMatchup());
        System.out.println(game);
    }

    @Test
    @Order(3)
    void testUpdate() throws PersistenciaDawException {
        Game game = gameRepository.getByID("0022200001");
        game.setMatchup("BOS X PHI");
        Game update = gameRepository.update(game);
        assertNotNull(update);
        assertEquals("BOS X PHI", update.getMatchup());
        System.out.println(update);
    }

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {
        List<Game> games = gameRepository.getAll();
        assertNotNull(games);
        assertFalse(games.isEmpty());
        for (Game game : games) {
            System.out.println(game);
        }
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        assertNotNull(gameRepository.getByID("0022200001"));
        assertNull(gameRepository.getByID("0022200002"));
    }
}
