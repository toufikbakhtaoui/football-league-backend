package org.bakhtaoui.team.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.domain.repository.TeamRepository;
import org.bakhtaoui.team.infrastructure.persistence.mapper.TeamEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultTeamRepository implements TeamRepository {

    private final TeamJpaRepository teamJpaRepository;
    private final TeamEntityMapper teamEntityMapper;

    @Override
    public List<Team> findAll() {
        var teams = teamJpaRepository.findAll();
        return teamEntityMapper.toDomainList(teams);
    }
}
