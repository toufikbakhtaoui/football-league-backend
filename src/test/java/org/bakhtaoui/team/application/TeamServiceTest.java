package org.bakhtaoui.team.application;

import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.application.mapper.TeamDtoMapperImpl;
import org.bakhtaoui.team.application.service.TeamService;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.helper.InMemoryTeamRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class TeamServiceTest {

    @Test
    void should_load_all_teams_from_repository() {
        Team team1 = Team.builder()
                .id(TeamIdentifier.builder().value(1L).build())
                .name("Patriots")
                .build();

        Team team2 = Team.builder()
                .id(TeamIdentifier.builder().value(2L).build())
                .name("Packers")
                .build();

        var inMemoryTeamRepository = new InMemoryTeamRepository(List.of(team1, team2));
        var teamMapper = new TeamDtoMapperImpl();

        TeamService teamService = new TeamService(inMemoryTeamRepository, teamMapper);

        var mappedTeam1 = teamMapper.toDto(team1);
        var mappedTeam2 = teamMapper.toDto(team2);
        assertThat(teamService.fetchAllTeams())
                .containsExactlyInAnyOrder(mappedTeam1, mappedTeam2);
    }

    @Test
    void returned_collection_should_be_immutable() {
        Team team = Team.builder()
                .id(TeamIdentifier.builder().value(1L).build())
                .name("Patriots")
                .build();

        var inMemoryTeamRepository = new InMemoryTeamRepository(List.of(team));
        var teamMapper = new TeamDtoMapperImpl();
        TeamService catalog = new TeamService(inMemoryTeamRepository, teamMapper);

        var mappedTeam = teamMapper.toDto(team);
        assertThatThrownBy(() ->
                catalog.fetchAllTeams().add(mappedTeam)
        ).isInstanceOf(UnsupportedOperationException.class);
    }
}
