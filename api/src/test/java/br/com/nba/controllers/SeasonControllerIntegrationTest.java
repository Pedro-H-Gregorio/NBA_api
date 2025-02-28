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

    private final String BASE_URL = "http://localhost:%d/api";
    private final String TEST_CREDENTIALS = "{\"username\": \"teste\", \"password\": \"teste\"}";

    private String token = null;

    private String getBaseUrl() {
        return String.format(BASE_URL, port);
    }

    private String getSeasonUrl() {
        return getBaseUrl() + "/season";
    }

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (token == null) {
            HttpEntity<String> requestToken = new HttpEntity<String>(TEST_CREDENTIALS, headers);
            restTemplate.postForEntity(getBaseUrl()
                    + "/auth/register", requestToken,
                    Void.class);
            ResponseEntity<String> responseToken = restTemplate.postForEntity(getBaseUrl() + "/auth/login",
                    requestToken,
                    String.class);
            this.token = responseToken.getBody();
        }

        headers.setBearerAuth(token);

        return headers;
    }

    @Test
    @Order(1)
    void testCreateSeasonForbidden() {
        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        ResponseEntity<Season> response = restTemplate.postForEntity(getSeasonUrl(), season,
                Season.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(2)
    void testCreateSeason() {
        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        HttpEntity<Season> requestEntity = new HttpEntity<>(season, getAuthenticatedHeaders());
        ResponseEntity<Season> response = restTemplate.postForEntity(getSeasonUrl(), requestEntity,
                Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024", response.getBody().getId());
        assertEquals("2023-24", response.getBody().getYear());
    }

    @Test
    @Order(3)
    void testGetSeasonByIdForbidden() {
        ResponseEntity<Season> response = restTemplate.getForEntity(getSeasonUrl() + "/2024",
                Season.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(4)
    void testGetSeasonById() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<Season> response = restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.GET,
                requestEntity, Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024", response.getBody().getId());
        assertEquals("2023-24", response.getBody().getYear());
        System.out.println(response.getBody());
    }

    @Test
    @Order(5)
    void testUpdateSeasonForbidden() {
        Season updatedSeason = new Season();
        updatedSeason.setId("2024");
        updatedSeason.setYear("2024-25");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Season> requestEntity = new HttpEntity<>(updatedSeason, headers);

        ResponseEntity<Season> response = restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.PUT, requestEntity,
                Season.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(6)
    void testUpdateSeason() {
        Season updatedSeason = new Season();
        updatedSeason.setId("2024");
        updatedSeason.setYear("2024-25");

        HttpHeaders headers = getAuthenticatedHeaders();

        HttpEntity<Season> requestEntity = new HttpEntity<>(updatedSeason, headers);

        ResponseEntity<Season> response = restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.PUT, requestEntity,
                Season.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("2024-25", response.getBody().getYear());
        System.out.println(response.getBody());
    }

    @Test
    @Order(7)
    void testGetAllSeasonsForbidden() {
        ResponseEntity<Season[]> response = restTemplate.getForEntity(getSeasonUrl(), Season[].class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(8)
    void testGetAllSeasons() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<Season[]> response = restTemplate.exchange(getSeasonUrl(),
                HttpMethod.GET,
                requestEntity, Season[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test
    @Order(9)
    void testDeleteSeasonForbidden() {
        restTemplate.delete(getSeasonUrl() + "/2024");

        ResponseEntity<Season> response = restTemplate.getForEntity(getSeasonUrl() + "/2024",
                Season.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(10)
    void testDeleteSeason() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.DELETE,
                requestEntity, Void.class);

        ResponseEntity<Season> response = restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.GET,
                requestEntity, Season.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
