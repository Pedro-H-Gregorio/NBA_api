package br.com.nba.repositories;

import br.com.nba.entities.*;
import br.com.nba.repositories.impl.PlayerSeasonRepositoryImpl;
import br.com.nba.repositories.impl.SeasonTeamRepositoryImpl;
import br.com.nba.repositories.interfaces.PlayerSeasonRepository;
import br.com.nba.repositories.interfaces.SeasonTeamRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerSeasonRepositoryTest {

    private PlayerSeasonRepository playerSeasonRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        playerSeasonRepository = new PlayerSeasonRepositoryImpl(emf);
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
    }
}
