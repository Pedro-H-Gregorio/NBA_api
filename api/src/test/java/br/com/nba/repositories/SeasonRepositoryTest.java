package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
import br.com.nba.api.repositories.PersistenciaDawException;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
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
class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository repository;


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
        System.out.println(seasons);
    }

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {
        repository.delete("2024");
        assertNull(repository.getByID("2024"));
    }
}
