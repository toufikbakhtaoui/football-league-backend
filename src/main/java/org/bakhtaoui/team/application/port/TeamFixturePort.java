package org.bakhtaoui.team.application.port;

import org.bakhtaoui.team.domain.view.TeamFixtureView;

import java.util.List;

public interface TeamFixturePort {
    List<TeamFixtureView> getTeams();
}
