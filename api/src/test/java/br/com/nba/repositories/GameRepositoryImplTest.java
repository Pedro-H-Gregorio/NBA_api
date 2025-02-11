package br.com.nba.repositories;

import br.com.nba.repositories.impl.GameRepositoryImpl;
import br.com.nba.repositories.interfaces.GameRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameRepositoryImplTest {
    private GameRepository gameRepository;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("nba_api");
        gameRepository = new GameRepositoryImpl(emf);
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
    }

    @Test
    @Order(2)
    void testGetByID() throws PersistenciaDawException {}

    @Test
    @Order(3)
    void testUpdate() throws PersistenciaDawException {}

    @Test
    @Order(4)
    void testGetAll() throws PersistenciaDawException {}

    @Test
    @Order(5)
    void testDelete() throws PersistenciaDawException {}
}
