package br.com.nba.controllers;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Team;
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
class TeamControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/team";
    }

    @Test
    @Order(1)
    void testCreateTeam() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        ResponseEntity<Team> response = restTemplate.postForEntity(getBaseUrl(), team, Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1610612737, response.getBody().getId());
        assertEquals("Atlanta", response.getBody().getCity());
        assertEquals("Hawks", response.getBody().getNickname());
    }

    @Test
    @Order(2)
    void testGetTeamById() {
        ResponseEntity<Team> response = restTemplate.getForEntity(getBaseUrl() + "/1610612737", Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1610612737, response.getBody().getId());
        assertEquals("Atlanta", response.getBody().getCity());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void testUpdateTeam() {
        Team updatedTeam = new Team();
        updatedTeam.setId(1610612737);
        updatedTeam.setCity("Atlanta Fake");
        updatedTeam.setNickname("Hawks");
        updatedTeam.setAbbreviation("ATL");
        updatedTeam.setFullName("Atlanta Hawks");
        updatedTeam.setYearFounded(1949);
        updatedTeam.setState("Georgia");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Team> requestEntity = new HttpEntity<>(updatedTeam, headers);

        ResponseEntity<Team> response = restTemplate.exchange(getBaseUrl() + "/1610612737", HttpMethod.PUT, requestEntity, Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Atlanta Fake", response.getBody().getCity());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void testGetAllTeams() {
        ResponseEntity<Team[]> response = restTemplate.getForEntity(getBaseUrl(), Team[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        Arrays.stream(response.getBody()).forEach(System.out::println);
    }

    @Test
    @Order(5)
    void testDeleteTeam() {
        restTemplate.delete(getBaseUrl() + "/1610612737");

        ResponseEntity<Team> response = restTemplate.getForEntity(getBaseUrl() + "/1610612737", Team.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
