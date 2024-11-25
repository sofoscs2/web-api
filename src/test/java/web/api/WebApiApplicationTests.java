package web.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import web.api.dto.MatchDto;
import web.api.dto.MatchOddsDto;
import web.api.dto.MatchOddsRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebApiApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Long  matchId;
    private Long  matchoddsId;

    @Test
    public void postMatch()  {
        MatchDto resource = validMatchRequest("beta", "Championship Final");
        ResponseEntity<MatchDto> response = restTemplate.postForEntity("/api/matches", resource, MatchDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Championship Final", response.getBody().getDescription());
    }

    @Test
    public void getMatchRequests()  {
        ResponseEntity<MatchDto[]> response = restTemplate.getForEntity("/api/matches", MatchDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<MatchDto> resources = Arrays.asList(response.getBody());
        assertFalse(resources.isEmpty());
        assertEquals("Basketball", resources.get(resources.size()-1).getSport());
        matchId = resources.get(resources.size()-1).getId();

        ResponseEntity<MatchDto> idResponse = restTemplate.getForEntity("/api/matches/"+matchId, MatchDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Basketball", idResponse.getBody().getSport());
    }

    @Test
    public void updateMatch()  {
        ResponseEntity<MatchDto[]> response = restTemplate.getForEntity("/api/matches", MatchDto[].class);

        List<MatchDto> resources = Arrays.asList(response.getBody());
        matchId = resources.get(resources.size()-1).getId();

        MatchDto updateRequest = validMatchRequest("omega", "Championship SemiFinal");
        ResponseEntity<MatchDto> putResponse = restTemplate.exchange(
                "/api/matches/"+matchId,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                MatchDto.class
        );

        assertEquals(HttpStatus.OK, putResponse.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Championship SemiFinal", putResponse.getBody().getDescription());
    }

    @Test
    public void postMatchOdds()  {
        ResponseEntity<MatchDto[]> matchResponse = restTemplate.getForEntity("/api/matches", MatchDto[].class);

        List<MatchDto> resources = Arrays.asList(matchResponse.getBody());
        matchId = resources.get(resources.size()-1).getId();

        MatchOddsRequest resource = validMatchOddsRequest(matchId, "X");

        ResponseEntity<MatchOddsDto> response = restTemplate.postForEntity("/api/match-odds", resource, MatchOddsDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("X", response.getBody().getSpecifier());
    }

    @Test
    public void getAllMatchesOdds()  {
        ResponseEntity<MatchOddsDto[]> response = restTemplate.getForEntity("/api/match-odds", MatchOddsDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<MatchOddsDto> resources = Arrays.asList(response.getBody());

        assertFalse(resources.isEmpty());
        assertEquals("X", resources.get(resources.size()-1).getSpecifier());
        matchoddsId = resources.get(resources.size()-1).getId();

        ResponseEntity<MatchOddsDto> idResponse = restTemplate.getForEntity("/api/match-odds/"+matchoddsId, MatchOddsDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("X", idResponse.getBody().getSpecifier());
    }

    @Test
    public void updateMatchOdds()  {
        ResponseEntity<MatchOddsDto[]> response = restTemplate.getForEntity("/api/match-odds", MatchOddsDto[].class);

        List<MatchOddsDto> resources = Arrays.asList(response.getBody());
        matchoddsId = resources.get(resources.size()-1).getId();

        MatchOddsRequest updateRequest = validMatchOddsRequest(resources.get(resources.size() - 1).getMatch().getId(), "1");

        ResponseEntity<MatchOddsDto> putResponse = restTemplate.exchange(
                "/api/match-odds/"+matchoddsId,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                MatchOddsDto.class
        );

        assertEquals(HttpStatus.OK, putResponse.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", putResponse.getBody().getSpecifier());
    }

    @Test
    public void deleteMatchOdds()  {
        ResponseEntity<MatchOddsDto[]> response = restTemplate.getForEntity("/api/match-odds", MatchOddsDto[].class);

        List<MatchOddsDto> resources = Arrays.asList(response.getBody());
        matchoddsId = resources.get(resources.size()-1).getId();

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/match-odds/"+matchoddsId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }

    @Test
    public void deleteMatch()  {
        ResponseEntity<MatchDto[]> response = restTemplate.getForEntity("/api/matches", MatchDto[].class);

        List<MatchDto> resources = Arrays.asList(response.getBody());
        matchId = resources.get(resources.size()-1).getId();

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                "/api/matches/"+matchId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
    }

    private static MatchDto validMatchRequest(String teamB, String description) {
        MatchDto resource = new MatchDto();
        resource.setDescription(description);
        resource.setMatchDate(LocalDate.now());
        resource.setMatchTime(LocalTime.now());
        resource.setSport("Basketball");
        resource.setTeamA("alpha");
        resource.setTeamB(teamB);
        return resource;
    }

    private MatchOddsRequest validMatchOddsRequest(Long matchId, String specifier) {
        MatchOddsRequest resource = new MatchOddsRequest();
        resource.setMatchId(matchId);
        resource.setOdd(1.5);
        resource.setSpecifier(specifier);
        return resource;
    }

}
