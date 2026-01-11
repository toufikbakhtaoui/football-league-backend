package org.bakhtaoui.team.web;

import org.bakhtaoui.team.web.dto.TeamResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureRestTestClient
class TeamControllerIT {

    @Autowired
    private RestTestClient restClient;

    @Test
    void should_return_teams() {
        restClient.get()
                .uri("/api/teams")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody(new ParameterizedTypeReference<List<TeamResponseDto>>() {})
                .value(teams ->
                        assertThat(teams)
                                .extracting(TeamResponseDto::name)
                                .contains("Patriots", "Packers")
                );
    }
}

