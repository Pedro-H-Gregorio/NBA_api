package br.com.nba.repositories;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
import br.com.nba.api.repositories.interfaces.SeasonRepository;
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
class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository repository;


    @Test
    @Order(1)
    void testSave() {
        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        assertDoesNotThrow(() -> repository.save(season));
    }

    @Test
    @Order(2)
    void testGetByID() {
        Optional<Season> season = repository.findById("2024");
        assertNotNull(season.get());
        assertEquals("2023-24", season.get().getYear());
        System.out.println(season);
    }

    @Test
    @Order(3)
    void testUpdate() {
        Optional<Season> season = repository.findById("2024");
        Season seasonToUpdate = season.get();
        seasonToUpdate.setYear("2024-25");
        Season updatedSeason = repository.save(seasonToUpdate);

        assertEquals("2024-25", updatedSeason.getYear());
    }

    @Test
    @Order(4)
    void testGetAll() {
        List<Season> seasons = repository.findAll();
        assertFalse(seasons.isEmpty());
        System.out.println(seasons);
    }

    @Test
    @Order(5)
    void testDelete() {
        repository.deleteById("2024");
        assertNull(repository.findById("2024").get());
    }
}
