package org.bakhtaoui.team.application.usecase;

import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.application.helper.InMemoryTeamRepository;
import org.bakhtaoui.team.domain.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetTeamsForFixturesUseCaseTest {

    private InMemoryTeamRepository repository;
    private GetTeamsForFixturesUseCase useCase;

    @BeforeEach
    void setup() {
        repository = new InMemoryTeamRepository();
        useCase = new GetTeamsForFixturesUseCase(repository);
    }

    @Test
    void should_expose_only_required_data_for_fixtures() {
        repository.save(team(1L, Conference.AFC, Division.WEST));

        var result = useCase.execute();

        assertThat(result)
                .hasSize(1)
                .first()
                .satisfies(view -> {
                    assertThat(view.id().value()).isEqualTo(1L);
                    assertThat(view.conference()).isEqualTo(Conference.AFC);
                    assertThat(view.division()).isEqualTo(Division.WEST);
                });
    }

    private Team team(Long id, Conference conf, Division div) {
        return new Team(
                new TeamIdentifier(id),
                "Team",
                "Code",
                "City",
                "Stadium",
                conf,
                div
        );
    }
}
