package br.com.nba.repositories;

import br.com.nba.entities.Team;
import br.com.nba.repositories.impl.TeamRepositoryImpl;
import br.com.nba.repositories.interfaces.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeamRepositoryTest {

    private TeamRepository teamRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        teamRepository = new TeamRepositoryImpl(emf);
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

        assertDoesNotThrow(() -> teamRepository.save(team));
        System.out.println(team);
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Team team = teamRepository.getByID(1610612737);
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
    void testUpdate() throws PersistenciaDawException {
        Team team = teamRepository.getByID(1610612737);
        team.setCity("Atlanta Fake");
        Team updatedTeam = teamRepository.update(team);
        assertNotNull(updatedTeam);
        assertEquals("Atlanta Fake", updatedTeam.getCity());
        System.out.println(team);
    }

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {
        List<Team> teams = teamRepository.getAll();
        assertNotNull(teams);
        assertFalse(teams.isEmpty());
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        teamRepository.delete(1610612737);
        assertNull(teamRepository.getByID(1610612737));
    }
}
