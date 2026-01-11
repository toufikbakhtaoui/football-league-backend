package org.bakhtaoui.team.infrastructure.persistence.mapper;

import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.infrastructure.persistence.entity.TeamEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamEntityMapper {

    Team toDomain(TeamEntity entity);

    default TeamIdentifier map(Long id) {
        return id == null ? null : new TeamIdentifier(id);
    }
}
