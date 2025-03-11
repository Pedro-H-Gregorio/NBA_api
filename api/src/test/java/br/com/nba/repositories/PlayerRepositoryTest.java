package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Player;
import br.com.nba.api.entities.Team;
import br.com.nba.api.repositories.interfaces.PlayerRepository;
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
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

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
        teamRepository.save(team);
        assertDoesNotThrow(() -> playerRepository.save(player));
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Optional<Player> player = playerRepository.findById(1631495);
        assertNotNull(player);
        assertEquals(1631495, player.get().getId());
        assertEquals("Donovan Williams", player.get().getName());
        assertEquals("Donovan", player.get().getNickName());
        assertEquals("G", player.get().getPosition());
        assertEquals("6-6", player.get().getHeight());
        assertEquals("190", player.get().getWeight());
        assertEquals("SEP 06, 2001", player.get().getBirthDate());
        assertEquals(21, player.get().getAge());
        assertEquals(1610612737, player.get().getTeam().getId());
        System.out.println(player);
    }

    @Test
    @Order(3)
    void testUpdate() throws PersistenciaDawException {
        Optional<Player> player = playerRepository.findById(1631495);
        Player playerToUpdate = player.get();
        playerToUpdate.setName("Donovan Williams Fake");
        Player updatedPlayer = playerRepository.save(playerToUpdate);
        assertNotNull(updatedPlayer);
        assertEquals("Donovan Williams Fake", updatedPlayer.getName());
        System.out.println(updatedPlayer);
    }

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {
        List<Player> players = playerRepository.findAll();
        assertNotNull(players);
        assertFalse(players.isEmpty());
        for (Player player : players) {
            System.out.println(player);
        }
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        assertDoesNotThrow(() -> playerRepository.deleteById(1631495));
        assertNull(playerRepository.findById(1631495).get());
        teamRepository.deleteById(1610612737);
    }
}
