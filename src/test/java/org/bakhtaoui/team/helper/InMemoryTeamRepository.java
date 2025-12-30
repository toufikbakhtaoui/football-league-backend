package org.bakhtaoui.team.helper;

import org.bakhtaoui.shared.identity.TeamIdentifier;
import org.bakhtaoui.team.domain.model.Team;
import org.bakhtaoui.team.domain.repository.TeamRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTeamRepository implements TeamRepository {

    private final Map<TeamIdentifier, Team> store = new HashMap<>();

    public InMemoryTeamRepository(List<Team> teams) {
        store.putAll(teams.stream().collect(Collectors.toMap(Team::id, t -> t)));
    }

    @Override
    public List<Team> findAll() {
        return new ArrayList<>(store.values());
    }
}
