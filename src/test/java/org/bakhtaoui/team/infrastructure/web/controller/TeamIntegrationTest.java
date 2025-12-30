package org.bakhtaoui.team.infrastructure.web.controller;

import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.team.helper.AbstractPostgresContainerTest;
import org.bakhtaoui.team.infrastructure.persistence.entity.TeamEntity;
import org.bakhtaoui.team.infrastructure.persistence.repository.TeamJpaRepository;
import org.bakhtaoui.team.infrastructure.web.dto.GetTeamsResponseDto;
import org.bakhtaoui.team.application.dto.TeamDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bakhtaoui.shared.enums.Conference.AFC;
import static org.bakhtaoui.shared.enums.Conference.NFC;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class TeamIntegrationTest extends AbstractPostgresContainerTest {

    @Autowired
    private RestTestClient restClient;

    @Autowired
    private TeamJpaRepository teamJpaRepository;

    @Test
    void should_fetch_teams_end_to_end() {
        //Given
        teamJpaRepository.save(
                new TeamEntity(
                        1L,
                        "Patriots",
                        null,
                        null,
                        AFC,
                        Division.EAST
                )
        );

        teamJpaRepository.save(
                new TeamEntity(
                        2L,
                        "Packers",
                        null,
                        null,
                        NFC,
                        Division.NORTH
                )
        );

        //WHen + Then
        restClient.get()
                .uri("/teams")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectBody(GetTeamsResponseDto.class)
                .value(response -> {
                    assertThat(response.teams())
                            .hasSize(2)
                            .extracting(TeamDto::id)
                            .containsExactlyInAnyOrder(1L, 2L);

                    assertThat(response.teams())
                            .extracting(TeamDto::conference)
                            .containsExactlyInAnyOrder(AFC, NFC);
                });
    }
}

