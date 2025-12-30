package org.bakhtaoui.team.application.mapper;

import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.application.dto.TeamDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamDtoMapper {

    List<TeamDto> toDtoList(List<Team> teams);

    @Mapping(source = "id.value", target = "id")
    TeamDto toDto(Team team);
}
