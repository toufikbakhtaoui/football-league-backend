package org.bakhtaoui.team.web.mapper;

import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.web.dto.TeamResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamWebMapper {

    TeamResponseDto toResponseDto(Team team);

    default Long map(TeamIdentifier identifier) {
        return identifier == null ? null : identifier.value();
    }
}
