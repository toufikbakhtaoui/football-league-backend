package org.bakhtaoui.team.infrastructure.web.mapper;

import org.bakhtaoui.shared.enums.Conference;
import org.bakhtaoui.shared.enums.Division;
import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.application.mapper.TeamDtoMapper;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.application.dto.TeamDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TeamDtoMapperTest {

    private final TeamDtoMapper mapper =
            Mappers.getMapper(TeamDtoMapper.class);

    @Test
    void should_map_team_to_dto() {
        Team team = new Team(
                new TeamIdentifier(1L),
                "Patriots",
                null,
                null,
                Conference.AFC,
                Division.EAST
        );

        TeamDto dto = mapper.toDto(team);

        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Patriots");
        assertThat(dto.conference()).isEqualTo(Conference.AFC);
        assertThat(dto.division()).isEqualTo( Division.EAST);
    }

    @Test
    void should_map_list_of_teams() {
        List<Team> teams = List.of(
                new Team(new TeamIdentifier(1L), "Patriots", null, null, Conference.AFC, Division.EAST),
                new Team(new TeamIdentifier(2L), "Packers", null,null, Conference.NFC, Division.NORTH)
        );

        List<TeamDto> dtos = mapper.toDtoList(teams);

        assertThat(dtos)
                .hasSize(2)
                .extracting(TeamDto::id)
                .containsExactlyInAnyOrder(1L, 2L);
    }
}

