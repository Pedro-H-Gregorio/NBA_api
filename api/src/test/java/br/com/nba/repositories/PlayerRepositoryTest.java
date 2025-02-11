package br.com.nba.repositories;

import br.com.nba.entities.Player;
import br.com.nba.entities.Team;
import br.com.nba.repositories.impl.PlayerRepositoryImpl;
import br.com.nba.repositories.interfaces.PlayerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerRepositoryTest {



    private PlayerRepository playerRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        playerRepository = new PlayerRepositoryImpl(emf);
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

        assertDoesNotThrow(() -> playerRepository.save(player));
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Player player = playerRepository.getByID(1631495);
        assertNotNull(player);
        assertEquals(1631495, player.getId());
        assertEquals("Donovan Williams", player.getName());
        assertEquals("Donovan", player.getNickName());
        assertEquals("G", player.getPosition());
        assertEquals("6-6", player.getHeight());
        assertEquals("190", player.getWeight());
        assertEquals("SEP 06, 2001", player.getBirthDate());
        assertEquals(21, player.getAge());
        assertEquals(1610612737, player.getTeam().getId());
        System.out.println(player);
    }

    @Test
    @Order(3)
    void testUpdate() throws PersistenciaDawException {
        Player player = playerRepository.getByID(1631495);
        player.setName("Donovan Williams Fake");
        Player updatedPlayer = playerRepository.update(player);
        assertNotNull(updatedPlayer);
        assertEquals("Donovan Williams Fake", updatedPlayer.getName());
        System.out.println(updatedPlayer);
    }

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {
        List<Player> players = playerRepository.getAll();
        assertNotNull(players);
        assertFalse(players.isEmpty());
        for (Player player : players) {
            System.out.println(player);
        }
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        assertDoesNotThrow(() -> playerRepository.delete(1631495));
        assertNull(playerRepository.getByID(1631495));
    }
}
