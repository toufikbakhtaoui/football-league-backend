package org.bakhtaoui.team.application.usecase;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.domain.port.TeamRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllTeamsUseCase {

    private final TeamRepository repository;

    @Cacheable("teamsWebView")
    public List<Team> execute() {
        return repository.findAll();
    }
}
