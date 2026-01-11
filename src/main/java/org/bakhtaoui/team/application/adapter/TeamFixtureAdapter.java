package org.bakhtaoui.team.application.adapter;

import lombok.RequiredArgsConstructor;
import org.bakhtaoui.team.application.port.TeamFixturePort;
import org.bakhtaoui.team.application.usecase.GetTeamsForFixturesUseCase;
import org.bakhtaoui.team.domain.view.TeamFixtureView;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamFixtureAdapter implements TeamFixturePort {

    private final GetTeamsForFixturesUseCase getTeamsForFixturesUseCase;

    @Override
    public List<TeamFixtureView> getTeams() {
        return getTeamsForFixturesUseCase.execute();
    }
}
