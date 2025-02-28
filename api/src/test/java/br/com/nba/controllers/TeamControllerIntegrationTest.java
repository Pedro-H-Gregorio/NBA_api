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

    private final String BASE_URL = "http://localhost:%d/api";
    private final String TEST_CREDENTIALS = "{\"username\": \"teste\", \"password\": \"teste\"}";

    private String token = null;

    private String getBaseUrl() {
        return String.format(BASE_URL, port);
    }

    private String getTeamUrl() {
        return getBaseUrl() + "/team";
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
    void testCreateTeamForbidden() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        ResponseEntity<Team> response = restTemplate.postForEntity(getTeamUrl(), team, Team.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(2)
    void testCreateTeam() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        HttpEntity<Team> requestEntity = new HttpEntity<>(team, getAuthenticatedHeaders());
        ResponseEntity<Team> response = restTemplate.postForEntity(getTeamUrl(), requestEntity,
                Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1610612737, response.getBody().getId());
        assertEquals("Atlanta", response.getBody().getCity());
        assertEquals("Hawks", response.getBody().getNickname());
    }

    @Test
    @Order(3)
    void testGetTeamByIdForbidden() {
        ResponseEntity<Team> response = restTemplate.getForEntity(getTeamUrl() + "/1610612737", Team.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(4)
    void testGetTeamById() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<Team> response = restTemplate.exchange(getTeamUrl() + "/1610612737",
                HttpMethod.GET,
                requestEntity, Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1610612737, response.getBody().getId());
        assertEquals("Atlanta", response.getBody().getCity());
        System.out.println(response.getBody());
    }

    @Test
    @Order(5)
    void testUpdateTeamForbidden() {
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

        ResponseEntity<Team> response = restTemplate.exchange(getTeamUrl() + "/1610612737", HttpMethod.PUT,
                requestEntity, Team.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(6)
    void testUpdateTeam() {
        Team updatedTeam = new Team();
        updatedTeam.setId(1610612737);
        updatedTeam.setCity("Atlanta Fake");
        updatedTeam.setNickname("Hawks");
        updatedTeam.setAbbreviation("ATL");
        updatedTeam.setFullName("Atlanta Hawks");
        updatedTeam.setYearFounded(1949);
        updatedTeam.setState("Georgia");

        HttpHeaders headers = getAuthenticatedHeaders();
        HttpEntity<Team> requestEntity = new HttpEntity<>(updatedTeam, headers);

        ResponseEntity<Team> response = restTemplate.exchange(getTeamUrl() + "/1610612737", HttpMethod.PUT,
                requestEntity, Team.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Atlanta Fake", response.getBody().getCity());
        System.out.println(response.getBody());
    }

    @Test
    @Order(7)
    void testGetAllTeamsForbidden() {
        ResponseEntity<Team[]> response = restTemplate.getForEntity(getTeamUrl(), Team[].class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(8)
    void testGetAllTeams() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<Team[]> response = restTemplate.exchange(getTeamUrl(),
                HttpMethod.GET,
                requestEntity, Team[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        Arrays.stream(response.getBody()).forEach(System.out::println);
    }

    @Test
    @Order(9)
    void testDeleteTeamForbidden() {
        restTemplate.delete(getBaseUrl() + "/1610612737");

        ResponseEntity<Team> response = restTemplate.getForEntity(getTeamUrl() + "/1610612737", Team.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(10)
    void testDeleteTeam() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        restTemplate.exchange(getTeamUrl() + "/1610612737",
                HttpMethod.DELETE,
                requestEntity, Void.class);

        ResponseEntity<Team> response = restTemplate.exchange(getTeamUrl() + "/1610612737",
                HttpMethod.GET,
                requestEntity, Team.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
