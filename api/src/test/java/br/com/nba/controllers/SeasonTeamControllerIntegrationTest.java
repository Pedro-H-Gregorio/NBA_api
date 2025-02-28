package br.com.nba.controllers;

import br.com.nba.api.ApiApplication;
import br.com.nba.api.entities.Season;
import br.com.nba.api.entities.SeasonTeam;
import br.com.nba.api.entities.Team;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SeasonTeamControllerIntegrationTest {

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

    private String getTeamUrl() {
        return getBaseUrl() + "/team";
    }

    private String getSeasonTeamUrl() {
        return getBaseUrl() + "/season-teams";
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
    void testCreateSeasonTeamForbidden() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        SeasonTeam seasonTeam = new SeasonTeam();
        seasonTeam.setId(season.getId() + team.getId().toString());
        seasonTeam.setTeam(team);
        seasonTeam.setSeason(season);

        restTemplate.postForEntity(getSeasonUrl(), season, Season.class);
        restTemplate.postForEntity(getTeamUrl(), team, Team.class);

        ResponseEntity<SeasonTeam> response = restTemplate.postForEntity(getSeasonTeamUrl(), seasonTeam,
                SeasonTeam.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(2)
    void testCreateSeasonTeam() {
        Team team = new Team();
        team.setId(1610612737);
        team.setCity("Atlanta");
        team.setNickname("Hawks");
        team.setAbbreviation("ATL");
        team.setFullName("Atlanta Hawks");
        team.setYearFounded(1949);
        team.setState("Georgia");

        Season season = new Season();
        season.setId("2024");
        season.setYear("2023-24");

        SeasonTeam seasonTeam = new SeasonTeam();
        seasonTeam.setId(season.getId() + team.getId().toString());
        seasonTeam.setTeam(team);
        seasonTeam.setSeason(season);

        HttpEntity<Season> requestSeason = new HttpEntity<>(season, getAuthenticatedHeaders());
        HttpEntity<Team> requestTeam = new HttpEntity<>(team, getAuthenticatedHeaders());
        HttpEntity<SeasonTeam> requestSeasonTeam = new HttpEntity<>(seasonTeam, getAuthenticatedHeaders());

        restTemplate.postForEntity(getSeasonUrl(), requestSeason, Season.class);
        restTemplate.postForEntity(getTeamUrl(), requestTeam, Team.class);

        ResponseEntity<SeasonTeam> response = restTemplate.postForEntity(getSeasonTeamUrl(), requestSeasonTeam,
                SeasonTeam.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("20241610612737", response.getBody().getId());
    }

    @Test
    @Order(3)
    void testGetSeasonTeamByIdForbidden() {
        ResponseEntity<SeasonTeam> response = restTemplate.getForEntity(getSeasonTeamUrl() + "/20241610612737",
                SeasonTeam.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(4)
    void testGetSeasonTeamById() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<SeasonTeam> response = restTemplate.exchange(getSeasonTeamUrl() + "/20241610612737",
                HttpMethod.GET,
                requestEntity, SeasonTeam.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("20241610612737", response.getBody().getId());
        System.out.println(response.getBody());
    }

    @Test
    @Order(5)
    void testGetTeamsBySeasonForbidden() {
        ResponseEntity<SeasonTeam[]> response = restTemplate.getForEntity(getSeasonTeamUrl() + "/2024/teams",
                SeasonTeam[].class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(6)
    void testGetTeamsBySeason() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<SeasonTeam[]> response = restTemplate.exchange(getSeasonTeamUrl() + "/2024/teams",
                HttpMethod.GET,
                requestEntity, SeasonTeam[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        SeasonTeam[] seasonTeams = response.getBody();
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(7)
    void testGetAllSeasonTeamsForbidden() {
        ResponseEntity<SeasonTeam[]> response = restTemplate.getForEntity(getSeasonTeamUrl(), SeasonTeam[].class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(8)
    void testGetAllSeasonTeams() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());
        ResponseEntity<SeasonTeam[]> response = restTemplate.exchange(getSeasonTeamUrl(),
                HttpMethod.GET,
                requestEntity, SeasonTeam[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        SeasonTeam[] seasonTeams = response.getBody();
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(9)
    void testDeleteSeasonTeamForbidden() {
        restTemplate.delete(getSeasonTeamUrl() + "/20241610612737");
        restTemplate.delete(getSeasonUrl() + "/2024");
        restTemplate.delete(getTeamUrl() + "/1610612737");

        ResponseEntity<SeasonTeam> response = restTemplate.getForEntity(getSeasonTeamUrl() + "/20241610612737",
                SeasonTeam.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(10)
    void testDeleteSeasonTeam() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(getAuthenticatedHeaders());

        restTemplate.exchange(getSeasonTeamUrl() + "/20241610612737",
                HttpMethod.DELETE,
                requestEntity, Void.class);
        restTemplate.exchange(getSeasonUrl() + "/2024",
                HttpMethod.DELETE,
                requestEntity, Void.class);
        restTemplate.exchange(getTeamUrl() + "/1610612737",
                HttpMethod.DELETE,
                requestEntity, Void.class);

        ResponseEntity<SeasonTeam> response = restTemplate.exchange(getSeasonTeamUrl() + "/20241610612737",
                HttpMethod.GET,
                requestEntity, SeasonTeam.class);

        restTemplate.getForEntity(getSeasonTeamUrl() + "/20241610612737",
                SeasonTeam.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
