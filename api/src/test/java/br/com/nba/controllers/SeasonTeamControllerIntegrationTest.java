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

    @Test
    @Order(1)
    void testCreateSeasonTeam() {
        String BASE_URL = "http://localhost:" + port + "/api";
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

//        ResponseEntity<Season> responseSeason = restTemplate.postForEntity(BASE_URL + "/season", season, Season.class);
        ResponseEntity<Team> responseTeam = restTemplate.postForEntity(BASE_URL + "/team", team, Team.class);
        ResponseEntity<SeasonTeam> response = restTemplate.postForEntity(BASE_URL + "/season-teams", seasonTeam, SeasonTeam.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("20241610612737", response.getBody().getId());
    }

    @Test
    @Order(2)
    void testGetSeasonTeamById() {
        String BASE_URL = "http://localhost:" + port + "/api/season-teams";
        ResponseEntity<SeasonTeam> response = restTemplate.getForEntity(BASE_URL + "/20241610612737", SeasonTeam.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("20241610612737", response.getBody().getId());
        System.out.println(response.getBody());
    }

    @Test
    @Order(3)
    void testGetTeamsBySeason() {
        String BASE_URL = "http://localhost:" + port + "/api/season-teams";
        ResponseEntity<SeasonTeam[]> response = restTemplate.getForEntity(BASE_URL + "/2024/teams", SeasonTeam[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        SeasonTeam[] seasonTeams = response.getBody();
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(4)
    void testGetAllSeasonTeams() {
        String BASE_URL = "http://localhost:" + port + "/api/season-teams";
        ResponseEntity<SeasonTeam[]> response = restTemplate.getForEntity(BASE_URL, SeasonTeam[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
        SeasonTeam[] seasonTeams = response.getBody();
        for (SeasonTeam seasonTeam : seasonTeams) {
            System.out.println(seasonTeam);
        }
    }

    @Test
    @Order(5)
    void testDeleteSeasonTeam() {
        String BASE_URL = "http://localhost:" + port + "/api/season-teams";
        restTemplate.delete(BASE_URL + "/20241610612737");

        ResponseEntity<SeasonTeam> response = restTemplate.getForEntity(BASE_URL + "/20241610612737", SeasonTeam.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
