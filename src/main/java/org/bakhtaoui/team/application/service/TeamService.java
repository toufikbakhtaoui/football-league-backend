package org.bakhtaoui.team.application.service;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.domain.repository.TeamRepository;
import org.bakhtaoui.team.application.dto.TeamDto;
import org.bakhtaoui.team.application.mapper.TeamDtoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamDtoMapper teamDtoMapper;

    @Cacheable("teams")
    public Collection<TeamDto> fetchAllTeams() {
        var teams = teamRepository.findAll();
        return Collections.unmodifiableCollection(teamDtoMapper.toDtoList(teams));
    }
}

