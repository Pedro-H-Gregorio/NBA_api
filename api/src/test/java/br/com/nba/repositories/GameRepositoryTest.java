package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Game;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.interfaces.GameRepository;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
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
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @Order(1)
    void testSave() {
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

        seasonRepository.save(season);
        teamRepository.save(homeTeam);
        teamRepository.save(awayTeam);

        assertDoesNotThrow(() -> gameRepository.save(game));
    }

    @Test
    @Order(2)
    void testGetByID() {
        Optional<Game> game = gameRepository.findById("0022200001");
        assertNotNull(game.get());
        assertEquals("0022200001", game.get().getId());
        assertEquals("BOS vs. PHI", game.get().getMatchup());
        System.out.println(game);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Optional<Game> game = gameRepository.findById("0022200001");
        Game gameToUpdate = game.get();
        gameToUpdate.setMatchup("BOS X PHI");
        Game update = gameRepository.save(gameToUpdate);
        assertNotNull(update);
        assertEquals("BOS X PHI", update.getMatchup());
        System.out.println(update);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Game> games = gameRepository.findAll();
        assertNotNull(games);
        assertFalse(games.isEmpty());
        for (Game game : games) {
            System.out.println(game);
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        assertDoesNotThrow(() -> gameRepository.deleteById("0022200001"));
        assertNull(gameRepository.findById("0022200002").get());
        teamRepository.deleteById(1610612738);
        teamRepository.deleteById(1610612755);
        seasonRepository.deleteById("22022");
    }
}
