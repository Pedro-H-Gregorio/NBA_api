package br.com.nba.repositories;

import br.com.nba.entities.Season;
import br.com.nba.repositories.PersistenciaDawException;
import br.com.nba.repositories.impl.RepositoryBaseImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SeasonRepositoryTest {

    private RepositoryBaseImpl<Season, String> repository;
    private EntityManagerFactory emf;

    @BeforeAll
    void setup() {
        emf = Persistence.createEntityManagerFactory("nba_api");
        repository = new RepositoryBaseImpl<>(Season.class, emf);
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
        season.setId("2024");
        season.setYear("2023-24");

        assertDoesNotThrow(() -> repository.save(season));
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {
        Season season = repository.getByID("2024");
        assertNotNull(season);
        assertEquals("2023-24", season.getYear());
        System.out.println(season);
    }

    @Test
    @Order(3)
    void testUpdate() throws PersistenciaDawException {
        Season season = repository.getByID("2024");
        season.setYear("2024-25");
        Season updatedSeason = repository.update(season);

        assertEquals("2024-25", updatedSeason.getYear());
    }

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {
        List<Season> seasons = repository.getAll();
        assertFalse(seasons.isEmpty());
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        repository.delete("2024");
        assertNull(repository.getByID("2024"));
    }
}
