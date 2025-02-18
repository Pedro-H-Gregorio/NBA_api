package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.PlayerSeason;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.impl.PlayerSeasonRepositoryImpl;
import br.com.nba.api.repositories.interfaces.PlayerRepository;
import br.com.nba.api.repositories.interfaces.PlayerSeasonRepository;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
import br.com.nba.api.repositories.interfaces.TeamRepository;
import org.checkerframework.checker.units.qual.A;
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
public class PlayerSeasonRepositoryTest {

    @Autowired
    private PlayerSeasonRepository playerSeasonRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

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

        Player player = new Player();
        player.setId(1631495);
        player.setName("Donovan Williams");
        player.setNickName("Donovan");
        player.setPosition("G");
        player.setHeight("6-6");
        player.setWeight("190");
        player.setBirthDate("SEP 06, 2001");
        player.setAge(21);
        player.setTeam(team);

        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        PlayerSeason playerSeason = new PlayerSeason();
        playerSeason.setId(season.getId() + player.getId().toString());
        playerSeason.setSeason(season);
        playerSeason.setPlayer(player);
        teamRepository.save(team);
        seasonRepository.save(season);
        playerRepository.save(player);
        assertDoesNotThrow(()-> playerSeasonRepository.save(playerSeason)); ;
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        PlayerSeason playerSeason = playerSeasonRepository.getByID("2024"+1631495);
        assertNotNull(playerSeason);
        System.out.println(playerSeason);
    }

    @Test
    @Order(3)
    void testGetAll() throws PersistenciaDawException {
        List<PlayerSeason> playerSeasons = playerSeasonRepository.getAll();
        assertNotNull(playerSeasons);
        assertFalse(playerSeasons.isEmpty());
        for (PlayerSeason playerSeason : playerSeasons) {
            System.out.println(playerSeason);
        }
    }

    @Test
    @Order(4)
    void testDelete() throws PersistenciaDawException {
        assertDoesNotThrow(() -> playerSeasonRepository.delete("2024"+1631495));
        assertNull(playerSeasonRepository.getByID("2024"+1631495));
        playerRepository.delete(1631495);
        teamRepository.delete(1610612737);
        seasonRepository.delete("2024");
    }
}
