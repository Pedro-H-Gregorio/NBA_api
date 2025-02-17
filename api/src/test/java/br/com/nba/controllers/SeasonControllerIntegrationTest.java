package br.com.nba.controllers;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
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
class SeasonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;



    @Test
    @Order(1)
    void testCreateSeason() {
        String BASE_URL = "http://localhost:" + port + "/api/season";
        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        ResponseEntity<Season> response = restTemplate.postForEntity(BASE_URL, season, Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024", response.getBody().getId());
        assertEquals("2023-24", response.getBody().getYear());
    }

    @Test
    @Order(2)
    void testGetSeasonById() {
        String BASE_URL = "http://localhost:" + port + "/api/season";
        ResponseEntity<Season> response = restTemplate.getForEntity(BASE_URL + "/2024", Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024", response.getBody().getId());
        assertEquals("2023-24", response.getBody().getYear());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void testUpdateSeason() {
        String BASE_URL = "http://localhost:" + port + "/api/season";
        Season updatedSeason = new Season();
        updatedSeason.setId("2024");
        updatedSeason.setYear("2024-25");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Season> requestEntity = new HttpEntity<>(updatedSeason, headers);

        ResponseEntity<Season> response = restTemplate.exchange(BASE_URL + "/2024", HttpMethod.PUT, requestEntity, Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024-25", response.getBody().getYear());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void testGetAllSeasons() {
        String BASE_URL = "http://localhost:" + port + "/api/season";
        ResponseEntity<Season[]> response = restTemplate.getForEntity(BASE_URL, Season[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test
    @Order(5)
    void testDeleteSeason() {
        String BASE_URL = "http://localhost:" + port + "/api/season";
        restTemplate.delete(BASE_URL + "/2024");

        ResponseEntity<Season> response = restTemplate.getForEntity(BASE_URL + "/2024", Season.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}