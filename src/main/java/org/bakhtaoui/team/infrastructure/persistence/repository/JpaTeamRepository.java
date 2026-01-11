package org.bakhtaoui.team.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.domain.port.TeamRepository;
import org.bakhtaoui.team.infrastructure.persistence.mapper.TeamEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaTeamRepository implements TeamRepository {

    private final SpringTeamJpaRepository jpaRepository;
    private final TeamEntityMapper mapper;

    @Override
    public List<Team> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}

