package br.com.nba.controllers;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Player;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlayerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String BASE_URL;

    @BeforeEach
    void setUp() {
        BASE_URL = "http://localhost:" + port + "/api/player";
    }

    @Test
    @Order(1)
    void testCreatePlayer() {
        Player player = Player.builder()
                .id(1)
                .name("LeBron James")
                .nickName("King James")
                .shirtNumber("23")
                .position("Forward")
                .height("6'9\"")
                .weight("250 lbs")
                .birthDate("1984-12-30")
                .age(39)
                .build();

        ResponseEntity<Player> response = restTemplate.postForEntity(BASE_URL, player, Player.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("LeBron James", response.getBody().getName());
    }

    @Test
    @Order(2)
    void testGetPlayerById() {
        ResponseEntity<Player> response = restTemplate.getForEntity(BASE_URL + "/1", Player.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    @Order(3)
    void testUpdatePlayer() {
        Player updatedPlayer = Player.builder()
                .id(1)
                .name("LeBron James")
                .nickName("King James")
                .shirtNumber("6")
                .position("Forward")
                .height("6'9\"")
                .weight("250 lbs")
                .birthDate("1984-12-30")
                .age(39)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> requestEntity = new HttpEntity<>(updatedPlayer, headers);

        ResponseEntity<Player> response = restTemplate.exchange(BASE_URL + "/1", HttpMethod.PUT, requestEntity, Player.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("6", response.getBody().getShirtNumber());
    }

    @Test
    @Order(4)
    void testGetAllPlayers() {
        ResponseEntity<Player[]> response = restTemplate.getForEntity(BASE_URL, Player[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test
    @Order(5)
    void testDeletePlayer() {
        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + "/1", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<Player> getResponse = restTemplate.getForEntity(BASE_URL + "/1", Player.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}
