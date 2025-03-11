package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Team;
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
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    @Order(1)
    void testSave() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        assertDoesNotThrow(() -> teamRepository.save(team));
        System.out.println(team);
    }

    @Test
    @Order(2)
    void testGetByID() {
        Optional<Team> teamOptional = teamRepository.findById(1610612737);
        Team team = teamOptional.get();
        assertNotNull(team);
        assertEquals(1610612737, team.getId());
        assertEquals("Atlanta", team.getCity());
        assertEquals("Hawks", team.getNickname());
        assertEquals("ATL", team.getAbbreviation());
        assertEquals("Atlanta Hawks", team.getFullName());
        assertEquals(1949, team.getYearFounded());
        assertEquals("Georgia", team.getState());
        System.out.println(team);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Optional<Team> team = teamRepository.findById(1610612737);
        Team updatedTeam = team.get();
        updatedTeam.setCity("Atlanta Fake");
        Team result = teamRepository.save(updatedTeam);
        assertNotNull(result);
        assertEquals("Atlanta Fake", result.getCity());
        System.out.println(team);
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Team> teams = teamRepository.findAll();
        assertNotNull(teams);
        assertFalse(teams.isEmpty());
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    @Test
    @Order(5)
    void testDelete() {
        teamRepository.deleteById(1610612737);
        assertNull(teamRepository.findById(1610612737).get());
    }
}
