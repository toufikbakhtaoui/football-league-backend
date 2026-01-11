package org.bakhtaoui.team.application.usecase;

import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.application.helper.InMemoryTeamRepository;
import org.bakhtaoui.team.domain.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAllTeamsUseCaseTest {

    private InMemoryTeamRepository repository;
    private GetAllTeamsUseCase useCase;

    @BeforeEach
    void setup() {
        repository = new InMemoryTeamRepository();
        useCase = new GetAllTeamsUseCase(repository);
    }

    @Test
    void should_return_all_registered_teams() {
        repository.save(team(1L, "Patriots", Conference.AFC, Division.EAST));
        repository.save(team(2L, "Packers", Conference.NFC, Division.NORTH));

        var result = useCase.execute();

        assertThat(result)
                .hasSize(2)
                .extracting(Team::name)
                .containsExactlyInAnyOrder("Patriots", "Packers");
    }

    private Team team(Long id, String name, Conference conf, Division div) {
        return new Team(
                new TeamIdentifier(id),
                name,
                "Code",
                "City",
                "Stadium",
                conf,
                div
        );
    }
}
