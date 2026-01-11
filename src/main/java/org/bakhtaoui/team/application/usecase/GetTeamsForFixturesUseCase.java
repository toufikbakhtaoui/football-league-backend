package org.bakhtaoui.team.application.usecase;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.domain.view.TeamFixtureView;
import org.bakhtaoui.team.domain.port.TeamRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTeamsForFixturesUseCase {

    private final TeamRepository repository;

    @Cacheable("teamsFixtureView")
    public List<TeamFixtureView> execute() {
        return repository.findAll().stream()
                .map(team -> new TeamFixtureView(
                        team.id(),
                        team.conference(),
                        team.division()))
                .toList();
    }
}
